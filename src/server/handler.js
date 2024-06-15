const predictClassification = require("../services/inferenceServices");
const crypto = require("crypto");
  const mysql = require('promise-mysql');
  const bcrypt = require('bcrypt');
  const jwt = require('jsonwebtoken');
  const secretKey = 'secret_key';
  
  
  const createUnixSocketPool = async config => {
      return mysql.createPool({
        user: process.env.DB_USER, // e.g. 'my-db-user'
        password: process.env.DB_PASS, // e.g. 'my-db-password'
        database: process.env.DB_NAME, // e.g. 'my-database'
        socketPath: process.env.INSTANCE_UNIX_SOCKET, // e.g. '/cloudsql/project:region:instance'
      });
    };
    
    let pool;
    (async () => {
        pool = await createUnixSocketPool();
    })();
  
  const register = async (request, h) => {
      try {
          const {
              username,
              gender,
              email,
              password
          } = request.payload;
      
          if (!email || !password) {
              const response = h.response({
                  status: 'fail',
                  message: 'Please fill email and password',
                });
                response.code(400);
                return response;
          }
  
          // cek email di db
          const checkEmailQuery = 'SELECT * FROM users WHERE email = ?';
          const existingUser = await new Promise((resolve, reject) => {
              pool.query(checkEmailQuery, [email], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve(rows[0]);
                  }
              });
          });
  
          if (existingUser) {
              const response = h.response({
                  status: 'fail',
                  message: 'Email already exists',
              });
              response.code(400);
              return response;
          }
      
          const hashedPassword = await bcrypt.hash(password, 10);
      
          const query = "INSERT INTO users(username, gender, email, password) VALUES(?, ?, ?, ?)";
      
          await new Promise((resolve, reject) => {
              pool.query(query, [username, gender, email, hashedPassword], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve();
                  }
              });
          });
      
          const response = h.response({
              status: 'success',
              message: 'User created successfully',
          });
          response.code(200);
          return response;
      } catch (err) {
          const response = h.response({
            status: 'fail',
            message: err.message,
          });
          response.code(500);
          return response;
      }
  }
  
  const login = async (request, h) => {
      const { email, password } = request.payload;
  
      try {
          const query = "SELECT * FROM users WHERE email = ?";
  
          const user = await new Promise((resolve, reject) => {
              pool.query(query, [email], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve(rows[0]);
                  }
              });
          });
          
          if (!user){
              const response = h.response({
                  status: 'fail',
                  message: 'Account invalid',
              });
              response.code(400);
              return response;
          }
          
          const isPassValid = await bcrypt.compare(password, user.password);
          
          if (!isPassValid){
              const response = h.response({
                  status: 'fail',
                  message: 'Account invalid',
              });
              response.code(400);
              return response;
          }
          
          const token = jwt.sign({ userId : user.id }, 'secret_key');
      
          const response = h.response({
              status: 'success',
              message: 'login successful',
              username: user.username,
              email: user.email,
              gender: user.gender,
              data: { 
                  token
              },
          });
          response.code(200);
          return response;
      } catch (err) {
          const response = h.response({
              status: 'fail',
              message: err.message,
          });
          response.code(500);
          return response;
      }
  }
  
  const readUser = async (request, h) => {
      try {
          const token = request.headers.authorization.replace('Bearer ', '');
          let decodedToken;
  
          try{
              decodedToken = jwt.verify(token, 'secret_key');
          } catch (err) {
              const response = h.response({
                  status: 'missed',
                  message: 'User is not authorized!',
              });
              response.code(401);
              return response;
          }
  
          const userId = decodedToken.userId;
  
          const query = 'SELECT * FROM users WHERE id = ?';
          
          const user = await new Promise((resolve, reject) => {
              pool.query(query, [userId], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve(rows[0]);
                  }
              });
          });
  
          if (!user){
              const response = h.response({
                  status: 'fail',
                  message: 'User is not found!',
              });
              response.code(400);
              return response;
          }
  
          const { password, ...userData } = user;
  
          const response = h.response({
              status: 'success',
              message: 'read successful',
              data: userData,
          });
          response.code(200);
          return response;
      } catch (err) {
          const response = h.response({
              status: 'fail',
              message: err.message,
          });
          response.code(500);
          return response;
      }
  }
  
  const updateUser = async (request, h) => {
      const { 
          username,
          gender,
          email,
          password
      } = request.payload;
  
      const token = request.headers.authorization.replace('Bearer ', '');
      let decodedToken;
  
      try{
          decodedToken = jwt.verify(token, 'secret_key');
      } catch (err) {
          const response = h.response({
              status: 'missed',
              message: 'User is not authorized!',
          });
          response.code(401);
          return response;
      }
  
      const userId = decodedToken.userId;
  
      try {
          const hashedPassword = await bcrypt.hash(password, 10);
          const query = 'UPDATE users SET username = ?, gender = ?, email = ?, password = ? WHERE id = ?';
          
          // will add userId later
          await new Promise((resolve, reject) => {
              pool.query(query, [username, gender, email, hashedPassword, userId], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve();
                  }
              });
          });
  
          const response = h.response({
              status: 'success',
              message: 'update successful',
          });
          response.code(200);
          return response;
      } catch (err) {
          const response = h.response({
              status: 'fail',
              message: err.message,
          });
          response.code(500);
          return response;
      }
  }
  
  const deleteUser = async (request, h) => {
      try {
          const token = request.headers.authorization.replace('Bearer ', '');
          const { password } = request.payload;
          let decodedToken;
  
          try {
              decodedToken = jwt.verify(token, 'secret_key');
          } catch (err) {
              const response = h.response({
                  status: 'missed',
                  message: 'User is not authorized!',
              });
              response.code(401);
              return response;
          }
  
          const userId = decodedToken.userId;
  
          // Get user from database
          const getUserQuery = 'SELECT password FROM users WHERE id = ?';
          const user = await new Promise((resolve, reject) => {
              pool.query(getUserQuery, [userId], (err, rows) => {
                  if (err) {
                      reject(err);
                  } else if (rows.length === 0) {
                      reject(new Error('User not found'));
                  } else {
                      resolve(rows[0]);
                  }
              });
          });
  
          // Validate password
          const isPasswordValid = await bcrypt.compare(password, user.password);
          if (!isPasswordValid) {
              const response = h.response({
                  status: 'fail',
                  message: 'Invalid password',
              });
              response.code(401);
              return response;
          }
  
          // Delete user
          const deleteUserQuery = 'DELETE FROM users WHERE id = ?';
          await new Promise((resolve, reject) => {
              pool.query(deleteUserQuery, [userId], (err, rows, field) => {
                  if (err) {
                      reject(err);
                  } else {
                      resolve();
                  }
              });
          });
  
          const response = h.response({
              status: 'success',
              message: 'Delete successful',
          });
          response.code(200);
          return response;
  
      } catch (err) {
          const response = h.response({
              status: 'fail',
              message: err.message,
          });
          response.code(500);
          return response;
      }
  };
   
  
  
//const { storeData, getDatas } = require("../services/storeData");

const color_Code_Skin = {
    light: ["#feddbe"],
    dark: ["#754f3c"],
    "mid-light": ["#e2a371"],
    "mid-dark": ["#906d60"]
  };  

const color_palette = {
    light: ["#ffffff", "#ffc8dd", "#ffafcc", "#bde0fe"],
    dark: ["#03045e", "#832161", "#363062", "#751628"],
    "mid-light": ["#fff8e7", "#b91d2e", "#a2d6f9", "#fd969a"],
    "mid-dark": ["#8c001a", "#d7c0d0", "#64113f", "#2e294e"]
  };

  const color_Name_Palette= {
    light: ["White", "Cherry Blush", "Dusty Pink", "Lavender blue"],
    dark: ["Navy", "Mauve", "Grey","Maroon", "Dark pink"],
    "mid-light": ["Cosmic Latte", "Red crimson","Sky blue", "Pink peach"],
    "mid-dark": ["Red cherry", "Lavender", "Purple", "Dark Grey"]
  };
  
//   const color_palette_img = {
//     light: [
//       "https://storage.googleapis.com/color_recommendation/light/ffffff.png",
//       "https://storage.googleapis.com/color_recommendation/light/ffc8dd.png",
//       "https://storage.googleapis.com/color_recommendation/light/ffafcc.png",
//       "https://storage.googleapis.com/color_recommendation/light/bde0fe.png"
//     ],
//     dark: [
//       "https://storage.googleapis.com/color_recommendation/dark/03045e.png",
//       "https://storage.googleapis.com/color_recommendation/dark/832161.png",
//       "https://storage.googleapis.com/color_recommendation/dark/363062.png",
//       "https://storage.googleapis.com/color_recommendation/dark/751628.png"
//     ],
//     "mid-light": [
//       "https://storage.googleapis.com/color_recommendation/mid-light/fff8e7.png",
//       "https://storage.googleapis.com/color_recommendation/mid-light/b91d2e.png",
//       "https://storage.googleapis.com/color_recommendation/mid-light/a2d6f9.png",
//       "https://storage.googleapis.com/color_recommendation/mid-light/fd969a.png"
//     ],
//     "mid-dark": [
//       "https://storage.googleapis.com/color_recommendation/mid-dark/8c001a.png",
//       "https://storage.googleapis.com/color_recommendation/mid-dark/d7c0d0.png",
//       "https://storage.googleapis.com/color_recommendation/mid-dark/64113f.png",
//       "https://storage.googleapis.com/color_recommendation/mid-dark/2e294e.png"
//     ]
//   };

 const color_Code_Jewelry= {
    light: ["#e2e5e6"],
    dark: ["#ffc536"],
    "mid-light": ["#e2e5e6"],
    "mid-dark": ["#ffc536"],
  };
  
  
  const color_jewelry = {
    light: ["silver"],
    dark: ["gold"],
    "mid-light": ["silver"],
    "mid-dark": ["gold"],
  };

  const getColorCodeSkin = (predictedClassName) => {
    return color_Code_Skin[predictedClassName] || [];
  };  
  
  const getColorRecommendation = (predictedClassName) => {
    return color_palette[predictedClassName] || [];
  };

  const getColorNamePalette = (predictedClassName) => {
    return color_Name_Palette[predictedClassName] || [];
  };

  const getColorCodeJewelry = (predictedClassName) => {
    return color_Code_Jewelry[predictedClassName] || [];
  };  
  
  const getColorJewelryRecommendation = (predictedClassName) => {
    return color_jewelry[predictedClassName] || [];
  };
  
//   const getColorPaletteRecommendation = (predictedClassName) => {
//     return color_palette_img[predictedClassName] || [];
//   };
  
  const postPredictHandler = async (request, h) => {
    try {
        const token = request.headers.authorization.replace('Bearer ', '');
          const { password } = request.payload;
          let decodedToken;
  
          try {
              decodedToken = jwt.verify(token, 'secret_key');
          } catch (err) {
              const response = h.response({
                  status: 'missed',
                  message: 'User is not authorized!',
              });
              response.code(401);
              return response;
          }
  
          const userId = decodedToken.userId;
      const { image } = request.payload;
  
      if (!image) {
        return h
          .response({ status: "error", message: "No image provided" })
          .code(400);
      }
  
      const { model } = request.server.app;
  
      const CLASS_NAMES = ["dark", "light", "mid-dark", "mid-light"];
  
      const { predictedClassName, predictions, predictedClassIndex } =
        await predictClassification(
          image, // Pass image data directly
          model,
          CLASS_NAMES
        );
  
      const id = crypto.randomUUID();
      const createdAt = new Date().toISOString();
  
      const colorCodeSkin= getColorCodeSkin(predictedClassName);
      const recommendation = getColorRecommendation(predictedClassName);
      const colorNamePalette= getColorNamePalette(predictedClassName);
      const colorCodeJewelry= getColorCodeJewelry(predictedClassName);
      const jewelryRecommendation = getColorJewelryRecommendation(predictedClassName);
    //   const colorPaletteImg = getColorPaletteRecommendation(predictedClassName);
  
      const newPrediction = {
        id,
        predictedClassName,
        colorCodeSkin,
        predictions,
        predictedClassIndex,
        createdAt,
        recommendation,
        colorNamePalette,
        colorCodeJewelry,
        jewelryRecommendation,
        // colorPaletteImg // Menambahkan rekomendasi perhiasan ke dalam objek newPrediction
      };
  
      // await storeData(id, newPrediction);
  
      return h
        .response({
          status: "success",
          message: "Model predicted successfully",
          data: newPrediction,
        })
        .code(201);
    } catch (error) {
      console.error("Error predicting:", error);
      return h
        .response({ status: "error", message: "Failed to predict" })
        .code(500);
    }
  };

module.exports = {register, login, readUser, updateUser, deleteUser, postPredictHandler};

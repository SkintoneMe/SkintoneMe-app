const {register, 
  login, 
  readUser, 
  updateUser, 
  deleteUser
} = require("../server/handler");

const routes = [
    {
        method: 'POST',
        path: '/register',
        handler: register,
    },

    {
        method: 'POST',
        path: '/login',
        handler: login,
    },

    {
        method: 'GET',
        path: '/readUser',
        handler: readUser,
    },

    {
        method: 'PUT',
        path: '/updateUser',
        handler: updateUser
    },

    {
        method: 'POST',
        path: '/deleteUser',
        handler: deleteUser
    },

  // {
  //   path: "/predict",
  //   method: "POST",
  //   handler: postPredictHandler,
  //   options: {
  //     payload: {
  //       allow: "multipart/form-data",
  //       multipart: true,
  //       maxBytes: 1000 * 1000,
  //     },
  //   },
  // },
];

module.exports = routes;

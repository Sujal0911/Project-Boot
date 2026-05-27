import React from "react";

const Popup = ({ message, show, type }) => {
  if (!show) return null;

  return (
    <div
      className={`fixed top-5 right-5 px-6 py-3 rounded-xl shadow-lg text-white z-50
      transition-all duration-300
      ${
        type === "success"
          ? "bg-green-500"
          : "bg-red-500"
      }`}
    >
      {message}
    </div>
  );
};

export default Popup;
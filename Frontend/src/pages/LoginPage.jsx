import React, { useState } from "react";
import { Eye, EyeOff, ShieldCheck, BadgeCheck, Users } from "lucide-react";
import { Navigate, useNavigate } from "react-router-dom";
import Popup from "../components/Popup";

function LoginPage() {

  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const [formData, setFormData] = useState({
    userId: "",
    password: "",
  });

    const [popup, setPopup] = useState({
    show: false,
    message: "",
    type: "success",
  });

  const showPopup = (message, type = "success") => {

    setPopup({
      show: true,
      message,
      type,
    });

    setTimeout(() => {
      setPopup({
        show: false,
        message: "",
        type: "success",
      });
    }, 3000);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // ================================
  // LOGIN API FUNCTION
  // ================================
  const handleSubmit = async (e) => {

  e.preventDefault();

  // USER ID VALIDATION
  if (!formData.userId.trim()) {
    alert("User ID is required");
    return;
  }

  // PASSWORD VALIDATION
  if (formData.password.length < 6) {
    alert("Password must be at least 6 characters");
    return;
  }

  try {

    const authString = btoa(
      formData.userId + ":" + formData.password
    );

    const response = await fetch(
      "http://localhost:8080/customer-user/all-services",
      {
        method: "GET",

        headers: {
          Authorization: "Basic " + authString,
        },
      }
    );

    if (response.ok) {

      
      localStorage.setItem("auth", authString);
      
      localStorage.setItem("userId", formData.userId);
      
      console.log(await response.json());
      
      navigate('/home');

    } else {

      showPopup("Invalid Credentials");

    }

  } catch (error) {

    console.log(error);

    alert("Server Error");

  }
};

  return (
    <div className="min-h-screen bg-[#f5f5f5] flex items-center justify-center p-5">
      <div className="max-w-full bg-white rounded-[35px] overflow-hidden shadow-xl grid grid-cols-1 lg:grid-cols-2">
        {/* LEFT IMAGE SECTION */}
        <div
          className="relative hidden lg:flex flex-col justify-center px-16 text-white"
          style={{
            backgroundImage:
              "url('https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?q=80&w=1600&auto=format&fit=crop')",
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
        >
          {/* Overlay */}
          <div className="absolute inset-0 bg-black/40"></div>

          <div className="relative z-10">
            <h1 className="text-6xl font-bold leading-tight mb-8">
              Welcome Back!
            </h1>

            <p className="text-2xl leading-relaxed text-gray-200 mb-16">
              Login to continue your journey with StayVista.
            </p>

            <div className="space-y-10">
              <div className="flex items-center gap-5">
                <ShieldCheck size={40} />
                <span className="text-2xl font-semibold">
                  Best price guarantee
                </span>
              </div>

              <div className="flex items-center gap-5">
                <BadgeCheck size={40} />
                <span className="text-2xl font-semibold">
                  Exclusive deals
                </span>
              </div>

              <div className="flex items-center gap-5">
                <Users size={40} />
                <span className="text-2xl font-semibold">
                  Easy & secure booking
                </span>
              </div>
            </div>
          </div>
        </div>

        {/* RIGHT LOGIN SECTION */}
        <div className="flex items-center justify-center px-6 py-14 md:px-16">
          <div className="w-full max-w-xl">
            {/* Logo */}
            <div className="flex items-center gap-3 mb-16">
              <div className="text-green-900 text-5xl font-bold">⌂</div>

              <h1 className="text-5xl font-bold">StayVista</h1>
            </div>

            {/* Heading */}
            <div className="mb-12">
              <h2 className="text-6xl font-bold mb-4">Login</h2>

              <p className="text-gray-500 text-2xl">
                Welcome back! Please login to your account.
              </p>
            </div>

            {/* FORM */}
            <form onSubmit={handleSubmit}>
              {/* Email */}
              <div className="mb-8">
                <label className="block text-2xl font-semibold mb-4">
                  User ID
                </label>

                <input
                  type="text"
                  name="userId"
                  placeholder="Enter your user ID"
                  value={formData.userId}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-6 py-5 text-xl outline-none focus:border-green-800"
                  required
                />
              </div>

              {/* Password */}
              <div className="mb-4">
                <label className="block text-2xl font-semibold mb-4">
                  Password
                </label>

                <div className="relative">
                  <input
                    type={showPassword ? "text" : "password"}
                    name="password"
                    placeholder="Enter your password"
                    value={formData.password}
                    onChange={handleChange}
                    className="w-full border border-gray-300 rounded-2xl px-6 py-5 text-xl outline-none focus:border-green-800"
                    required
                  />

                  <button
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                    className="cursor-pointer absolute right-5 top-1/2 -translate-y-1/2 text-gray-500"
                  >
                    {showPassword ? (
                      <EyeOff size={28} />
                    ) : (
                      <Eye size={28} />
                    )}
                  </button>
                </div>
              </div>

              {/* Forgot Password */}
              {/* <div className="flex justify-end mb-10">
                <button
                  type="button"
                  className="text-green-900 text-xl font-semibold"
                >
                  Forgot password?
                </button>
              </div> */}

              {/* Login Button */}
              <button
                type="submit"
                className="w-full bg-green-900 hover:bg-green-800 transition text-white py-5 rounded-2xl text-2xl font-semibold"
              >
                Login
              </button>

                <Popup
                  show={popup.show}
                  message={popup.message}
                  type={popup.type}
                />

            </form>

            {/* Signup */}
            <p className="text-center text-xl my-4">
              Don't have an account?{" "}
              <span onClick={() => navigate('/signup')} className="text-green-900 font-semibold cursor-pointer">
                Sign up
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;

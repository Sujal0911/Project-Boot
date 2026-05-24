import React, { useState } from "react";
import {
  Eye,
  EyeOff,
  ShieldCheck,
  BadgeCheck,
  Users,
} from "lucide-react";
import { Navigate, useNavigate } from "react-router-dom";

function SignUpPage() {
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    userId: "",
    password: "",
    age: "",
    email: "",
    address: "",
    contactNo: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // ======================================
  // SIGNUP FUNCTION
  // ======================================

  const handleSubmit = async (e) => {
    e.preventDefault();

    // VALIDATION

    if (!formData.firstName.trim()) {
      alert("First Name is required");
      return;
    }

    if (!formData.userId.trim()) {
      alert("User ID is required");
      return;
    }

    if (formData.password.length < 8) {
      alert("Password must be at least 8 characters");
      return;
    }

    if (!formData.email.trim()) {
      alert("Email is required");
      return;
    }

    if (!formData.address.trim()) {
      alert("Address is required");
      return;
    }

    if (!formData.contactNo.trim()) {
      alert("Contact Number is required");
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8080/customer-user/create-account",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify({
            firstName: formData.firstName,
            lastName: formData.lastName,
            userId: formData.userId,
            password: formData.password,
            role: "USER",
            age: Number(formData.age),
            email: formData.email,
            address: formData.address,
            contactNo: Number(formData.contactNo),
          }),
        }
      );

      if (response.ok) {
        alert("Account Created Successfully");

        window.location.href = "/login";
      } else {
        const errorData = await response.text();

        alert(errorData || "Signup Failed");
      }
    } catch (error) {
      console.log(error);

      alert("Server Error");
    }
  };

  return (
    <div className="min-h-screen bg-[#f5f5f5] flex items-center justify-center p-5">
      <div className="w-full max-w-7xl bg-white rounded-[35px] overflow-hidden shadow-xl grid grid-cols-1 lg:grid-cols-2">

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
          <div className="absolute inset-0 bg-black/45"></div>

          <div className="relative z-10">
            <h1 className="text-6xl font-bold leading-tight mb-8">
              Create Account
            </h1>

            <p className="text-2xl leading-relaxed text-gray-200 mb-16">
              Join StayVista and enjoy seamless bookings.
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

        {/* RIGHT SIGNUP SECTION */}

        <div className="flex items-center justify-center px-6 py-14 md:px-16">
          <div className="w-full max-w-xl">

            {/* LOGO */}

            <div className="flex items-center gap-3 mb-10">
              <div className="text-green-900 text-5xl font-bold">
                ⌂
              </div>

              <h1 className="text-5xl font-bold">
                StayVista
              </h1>
            </div>

            {/* HEADING */}

            <div className="mb-8">
              <h2 className="text-5xl font-bold mb-3">
                Sign Up
              </h2>

              <p className="text-gray-500 text-xl">
                Create an account to get started.
              </p>
            </div>

            {/* FORM */}

            <form onSubmit={handleSubmit}>

              {/* FIRST NAME */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  First Name
                </label>

                <input
                  type="text"
                  name="firstName"
                  placeholder="Enter first name"
                  value={formData.firstName}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                  required
                />
              </div>

              {/* LAST NAME */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  Last Name
                </label>

                <input
                  type="text"
                  name="lastName"
                  placeholder="Enter last name"
                  value={formData.lastName}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                />
              </div>

              {/* USER ID */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  User ID
                </label>

                <input
                  type="text"
                  name="userId"
                  placeholder="Create user ID"
                  value={formData.userId}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                  required
                />
              </div>

              {/* EMAIL */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  Email
                </label>

                <input
                  type="email"
                  name="email"
                  placeholder="Enter email"
                  value={formData.email}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                  required
                />
              </div>

              {/* AGE */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  Age
                </label>

                <input
                  type="number"
                  name="age"
                  placeholder="Enter age"
                  value={formData.age}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                />
              </div>

              {/* CONTACT NUMBER */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  Contact Number
                </label>

                <input
                  type="tel"
                  name="contactNo"
                  placeholder="Enter contact number"
                  value={formData.contactNo}
                  onChange={handleChange}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800 no"
                  required
                />
              </div>

              {/* ADDRESS */}

              <div className="mb-5">
                <label className="block text-lg font-semibold mb-2">
                  Address
                </label>

                <textarea
                  name="address"
                  placeholder="Enter address"
                  value={formData.address}
                  onChange={handleChange}
                  rows={3}
                  className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                  required
                />
              </div>

              {/* PASSWORD */}

              <div className="mb-8">
                <label className="block text-lg font-semibold mb-2">
                  Password
                </label>

                <div className="relative">
                  <input
                    type={showPassword ? "text" : "password"}
                    name="password"
                    placeholder="Create password"
                    value={formData.password}
                    onChange={handleChange}
                    className="w-full border border-gray-300 rounded-2xl px-5 py-4 text-lg outline-none focus:border-green-800"
                    required
                  />

                  <button
                    type="button"
                    onClick={() =>
                      setShowPassword(!showPassword)
                    }
                    className="cursor-pointer absolute right-5 top-1/2 -translate-y-1/2 text-gray-500"
                  >
                    {showPassword ? (
                      <EyeOff size={24} />
                    ) : (
                      <Eye size={24} />
                    )}
                  </button>
                </div>
              </div>

              {/* BUTTON */}

              <button
                type="submit"
                className="w-full bg-green-900 hover:bg-green-800 transition text-white py-4 rounded-2xl text-xl font-semibold"
              >
                Sign Up
              </button>
            </form>

            {/* LOGIN */}

            <p className="text-center text-lg mt-6">
              Already have an account?{" "}

              <span
                onClick={() =>
                  navigate("/login")
                }
                className="text-green-900 font-semibold cursor-pointer"
              >
                Login
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignUpPage;
import React, { useEffect, useState } from "react";

import {
  MapPin,
  Users,
  BedDouble,
  Trash2,
  CalendarDays,
  ShieldCheck,
  BadgeIndianRupee,
  Info,
} from "lucide-react";

import { Navigate, useNavigate } from "react-router-dom";

function CartPage() {
  const navigate = useNavigate();
  
  const [total, setTotal] = useState(0);

  const [cartItems, setCartItems] = useState([]);

  // =========================================
  // FETCH CART ITEMS
  // =========================================
  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = async () => {

    try {

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        "http://localhost:8080/booking/see-booking",
        {
          method: "GET",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      const data = await response.json();

      console.log("FULL DATA:", data);

      setCartItems(data.products || []);

      setTotal(data.total || 0);

    } catch (error) {

      console.log(error);

      alert("Failed to load cart");

    }
  };

  // =========================================
  // REMOVE CART ITEM
  // =========================================
  const removeCartItem = async (Id) => {

    try {

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        `http://localhost:8080/cart/delete-cartItemId/${Id}`,
        {
          method: "DELETE",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      if (response.ok) {

        alert("Item Removed");

        fetchCartItems();

      } else {

        alert("Failed to remove item");

      }

    } catch (error) {

      console.log(error);

      alert("Server Error");

    }
  };

  return (

    <div className="h-screen overflow-hidden bg-[#f7f7f7]">

      {/* NAVBAR */}
      <nav className="w-full bg-white border-b border-gray-200 px-6 md:px-10 py-5 flex items-center justify-between">
        <div className="flex items-center gap-2">
          <div className="text-green-900 font-bold text-3xl">⌂</div>
          <h1 className="text-3xl font-bold text-gray-900">StayVista</h1>
        </div>

        <ul className="hidden lg:flex items-center gap-12 font-medium text-gray-800">
          <li onClick={() => navigate("/home")} className="cursor-pointer hover:text-green-800">Home</li>
          <li onClick={() => navigate("/stays")} className="cursor-pointer hover:text-green-800">Stays</li>
          <li onClick={() => navigate("/services")} className="cursor-pointer hover:text-green-800">Services</li>
          <li onClick={() => navigate('/experiences')} className="cursor-pointer hover:text-green-800">Experiences</li>
          {/* <li className="cursor-pointer hover:text-green-800">About Us</li> */}
        </ul>

        <div className="flex items-center gap-5">
          {/* <button onClick={() => navigate("/login")} className="hidden md:block font-medium cursor-pointer">Login</button>

          <button onClick={() => navigate('/signup')} className="cursor-pointer bg-green-900 text-white px-5 py-3 rounded-xl font-semibold hover:bg-green-800 transition">
            Sign Up
          </button> */}

          {/* <Menu className="cursor-pointer" /> */}
        </div>
      </nav>

      {/* MAIN */}
      <div className="px-8 py-4 h-[calc(100vh-80px)] overflow-hidden">

        {/* PAGE TITLE */}
        <h1 className="text-4xl font-bold mb-5">
          Your Cart
        </h1>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-5 h-full">

          {/* LEFT SECTION */}
          <div className="lg:col-span-2 overflow-y-auto pr-2 space-y-5">

            {cartItems.length === 0 ? (

              <div className="bg-white rounded-[25px] p-10 shadow-sm text-center">

                <h2 className="text-3xl font-bold mb-3">
                  Cart is Empty
                </h2>

                <p className="text-gray-500 text-lg">
                  Add hotels or services to continue.
                </p>

              </div>

            ) : (

              cartItems.map((item) => (

  <div
    key={item.id}
    className="bg-white rounded-3xl p-4 shadow-sm hover:shadow-md transition"
  >

    <div className="flex gap-4">

      {/* IMAGE */}
      <img
        src={
          item.imageUrl ||
          "https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=1200&auto=format&fit=crop"
        }
        alt="hotel"
        className="w-[160px] h-[130px] object-cover rounded-2xl"
      />

      {/* CONTENT */}
      <div className="flex-1 flex flex-col justify-between">

        {/* TOP */}
        <div className="flex justify-between items-start">

          <div>

            <h2 className="text-2xl font-bold text-gray-900">
              {item.productName}
            </h2>

            <div className="flex items-center gap-2 text-gray-500 mt-1">

              <MapPin size={16} />

              <span className="text-sm">
                Goa, India
              </span>

            </div>

          </div>

          <div className="text-right">

            <h2 className="text-2xl font-bold text-green-900">
              ₹{item.price}
            </h2>

            <p className="text-sm text-gray-500">
              / booking
            </p>

          </div>
        </div>

        {/* DESCRIPTION */}
        <p className="text-gray-500 text-sm mt-2 line-clamp-2">
          {item.description}
        </p>

        {/* BOTTOM */}
        <div className="flex items-center justify-between mt-4">

          {/* INFO */}
          <div className="flex items-center gap-5 text-sm text-gray-600">

            <div className="flex items-center gap-1">

              <CalendarDays size={16} />

              <span>
                {item.duration || "1 hour"}
              </span>

            </div>

            <div className="flex items-center gap-1">

              <BedDouble size={16} />

              <span>
                {item.productType}
              </span>

            </div>
          </div>

          {/* REMOVE */}
          <button
            onClick={() => removeCartItem(item.id)}
            className="flex items-center gap-2 text-red-500 hover:text-red-600 text-sm font-medium"
          >

            <Trash2 size={16} />

            Remove

          </button>
        </div>
      </div>
    </div>
  </div>
))
            )}
          </div>

          {/* RIGHT SUMMARY */}
          <div className="bg-white rounded-[25px] p-5 shadow-sm h-fit sticky top-5 self-start">

            <h2 className="text-3xl font-bold mb-5">
              Summary
            </h2>

            <div className="flex justify-between items-center mb-5">

              <span className="text-2xl font-semibold">
                Total
              </span>

              <span className="text-4xl font-bold">
                ₹{total}
              </span>

            </div>

            {/* CHECKOUT BUTTON */}
            <button className="w-full bg-green-900 hover:bg-green-800 transition text-white py-4 rounded-2xl text-xl font-semibold mb-4">
              Proceed to Checkout
            </button>

            <p className="text-center text-gray-500 text-sm mb-4">
              You won't be charged yet
            </p>

            <div className="border-t border-gray-200 mb-4"></div>

            {/* FEATURES */}
            <div className="space-y-5">

              <div className="flex gap-4">

                <div className="bg-green-50 p-3 rounded-full h-fit">

                  <ShieldCheck
                    size={24}
                    className="text-green-900"
                  />

                </div>

                <div>

                  <h3 className="text-lg font-bold mb-1">
                    Best Price Guarantee
                  </h3>

                  <p className="text-gray-500 text-sm">
                    Get the best prices always
                  </p>

                </div>
              </div>

              <div className="flex gap-4">

                <div className="bg-green-50 p-3 rounded-full h-fit">

                  <BadgeIndianRupee
                    size={24}
                    className="text-green-900"
                  />

                </div>

                <div>

                  <h3 className="text-lg font-bold mb-1">
                    Secure Payment
                  </h3>

                  <p className="text-gray-500 text-sm">
                    Your payment is 100% secure
                  </p>

                </div>
              </div>

              <div className="flex gap-4">

                <div className="bg-green-50 p-3 rounded-full h-fit">

                  <Info
                    size={24}
                    className="text-green-900"
                  />

                </div>

                <div>

                  <h3 className="text-lg font-bold mb-1">
                    Free Cancellation
                  </h3>

                  <p className="text-gray-500 text-sm">
                    Cancel for free up to 24 hours before check-in
                  </p>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CartPage;
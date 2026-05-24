import React, { useEffect, useState } from "react";
import { Menu, MapPin, CalendarDays, Users } from "lucide-react";

function OrderHistoryPage() {

  const [orders, setOrders] = useState([]);

  const [activeTab, setActiveTab] = useState("ALL");

  // =========================================
  // FETCH ORDER HISTORY
  // =========================================
  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {

    try {

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        "http://localhost:8080/customer-user/order-history",
        {
          method: "GET",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      const data = await response.json();

      console.log(data);

      setOrders(data);

    } catch (error) {

      console.log(error);

      alert("Failed to load order history");

    }
  };

  // =========================================
  // FILTER ORDERS
  // =========================================
  const filteredOrders = orders.filter((order) => {

    if (activeTab === "ALL") {
      return true;
    }

    return order.status === activeTab;

  });

  return (

    <div className="min-h-screen bg-[#f7f7f7] p-4">

      {/* ========================================= */}
      {/* MAIN CONTAINER */}
      {/* ========================================= */}

      <div className="bg-white rounded-[35px] shadow-sm min-h-screen">

        {/* ========================================= */}
        {/* NAVBAR */}
        {/* ========================================= */}

        <div className="flex items-center justify-between px-10 py-6 border-b border-gray-100">

          {/* LOGO */}
          <div className="flex items-center gap-3">

            <div className="text-green-900 text-5xl font-bold">
              ⌂
            </div>

            <h1 className="text-4xl font-bold">
              StayVista
            </h1>
          </div>

          {/* MENU */}
          <div className="hidden lg:flex items-center gap-16 text-2xl font-medium">

            <span className="cursor-pointer hover:text-green-800">
              Home
            </span>

            <span className="cursor-pointer hover:text-green-800">
              Stays
            </span>

            <span className="cursor-pointer hover:text-green-800">
              Services
            </span>

            <span className="cursor-pointer hover:text-green-800">
              Experiences
            </span>

            <span className="cursor-pointer hover:text-green-800">
              About
            </span>
          </div>

          {/* RIGHT */}
          <div className="flex items-center gap-6">

            <button className="bg-green-900 hover:bg-green-800 transition text-white px-8 py-4 rounded-2xl text-2xl font-semibold">
              Sign Up
            </button>

            <Menu size={42} />
          </div>
        </div>

        {/* ========================================= */}
        {/* PAGE CONTENT */}
        {/* ========================================= */}

        <div className="px-10 py-12">

          {/* TITLE */}
          <h1 className="text-6xl font-bold mb-14">
            Order History
          </h1>

          {/* ========================================= */}
          {/* TABS */}
          {/* ========================================= */}

          <div className="flex items-center gap-20 border-b border-gray-200 mb-10">

            <button
              onClick={() => setActiveTab("ALL")}
              className={`pb-5 text-2xl font-semibold transition ${
                activeTab === "ALL"
                  ? "text-green-900 border-b-4 border-green-900"
                  : "text-gray-500"
              }`}
            >
              All
            </button>

            <button
              onClick={() => setActiveTab("COMPLETED")}
              className={`pb-5 text-2xl font-semibold transition ${
                activeTab === "COMPLETED"
                  ? "text-green-900 border-b-4 border-green-900"
                  : "text-gray-500"
              }`}
            >
              Completed
            </button>

            <button
              onClick={() => setActiveTab("CANCELLED")}
              className={`pb-5 text-2xl font-semibold transition ${
                activeTab === "CANCELLED"
                  ? "text-green-900 border-b-4 border-green-900"
                  : "text-gray-500"
              }`}
            >
              Cancelled
            </button>
          </div>

          {/* ========================================= */}
          {/* ORDER LIST */}
          {/* ========================================= */}

          <div className="space-y-8">

            {filteredOrders.length === 0 ? (

              <div className="bg-white border border-gray-200 rounded-[30px] p-16 text-center">

                <h2 className="text-4xl font-bold mb-4">
                  No Orders Found
                </h2>

                <p className="text-gray-500 text-2xl">
                  Your bookings will appear here.
                </p>

              </div>

            ) : (

              filteredOrders.map((order) => (

                <div
                  key={order.id}
                  className="bg-white border border-gray-200 rounded-[30px] p-8 shadow-sm"
                >

                  <div className="flex flex-col xl:flex-row gap-8 xl:items-center justify-between">

                    {/* LEFT */}
                    <div className="flex gap-8">

                      {/* IMAGE */}
                      <img
                        src={
                          order.imageUrl ||
                          "https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=1200&auto=format&fit=crop"
                        }
                        alt="hotel"
                        className="w-220px h-170px rounded-[25px] object-cover"
                      />

                      {/* HOTEL DETAILS */}
                      <div>

                        <h2 className="text-4xl font-bold mb-5">
                          {order.serviceName || "Sea Breeze Hotel"}
                        </h2>

                        <div className="flex items-center gap-3 text-gray-700 text-2xl mb-6">
                          <MapPin size={26} />

                          <span>
                            {order.location || "Goa"}
                          </span>
                        </div>

                        <div className="flex items-center gap-10 text-gray-700 text-2xl">

                          <div className="flex items-center gap-3">
                            <CalendarDays size={24} />

                            <span>
                              {order.checkIn || "20 May"} - {order.checkOut || "23 May"}
                            </span>
                          </div>

                          <div className="flex items-center gap-3">
                            <Users size={24} />

                            <span>
                              {order.guests || 2} Guests
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>

                    {/* RIGHT */}
                    <div className="flex flex-col items-start xl:items-end gap-6">

                      <h2 className="text-5xl font-bold">
                        ₹{order.totalAmount || 14850}
                      </h2>

                      <div
                        className={`px-6 py-3 rounded-xl text-xl font-semibold ${
                          order.status === "COMPLETED"
                            ? "bg-green-100 text-green-800"
                            : "bg-red-100 text-red-700"
                        }`}
                      >
                        {order.status || "COMPLETED"}
                      </div>
                    </div>
                  </div>
                </div>
              ))
            )}
          </div>

          {/* ========================================= */}
          {/* LOAD MORE */}
          {/* ========================================= */}

          <div className="flex justify-center mt-14">

            <button className="text-green-900 text-3xl font-bold hover:underline">
              Load more
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OrderHistoryPage;
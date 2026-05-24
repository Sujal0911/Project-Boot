import React from "react";
import {Menu, Search, Star, Heart, Wifi, Headphones, ShieldCheck, Car, Building2, } from "lucide-react";
import { useNavigate } from "react-router-dom";
import CartIcon from "../components/CartIcon";
import { UserCircle2 } from "lucide-react";

const hotels = [
  {
    name: "Sea Breeze Hotel",
    location: "Calangute, Goa",
    rating: "4.7",
    price: "₹4,500",
    image:
      "https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=1200&auto=format&fit=crop",
  },
  {
    name: "The Oceanic Resort",
    location: "Puducherry",
    rating: "4.6",
    price: "₹5,200",
    image:
      "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?q=80&w=1200&auto=format&fit=crop",
  },
  {
    name: "Green Valley Guesthouse",
    location: "Munnar, Kerala",
    rating: "4.8",
    price: "₹3,200",
    image:
      "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=1200&auto=format&fit=crop",
  },
  {
    name: "Sunset Bay Hotel",
    location: "Visakhapatnam",
    rating: "4.5",
    price: "₹4,000",
    image:
      "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?q=80&w=1200&auto=format&fit=crop",
  },
];

const services = [
  {
    title: "Free Wi-Fi",
    subtitle: "Stay connected",
    icon: <Wifi size={34} />,
  },
  {
    title: "Room Service",
    subtitle: "Delicious meals",
    icon: <Building2 size={34} />,
  },
  {
    title: "Airport Pickup",
    subtitle: "Hassle-free transfer",
    icon: <Car size={34} />,
  },
  {
    title: "24/7 Support",
    subtitle: "We're here for you",
    icon: <Headphones size={34} />,
  },
  {
    title: "Secure Booking",
    subtitle: "Safe, fast and easy",
    icon: <ShieldCheck size={34} />,
  },
];
function HomePage() {

  const isLoggedIn = localStorage.getItem("auth");

  const navigate = useNavigate();

  return (
    <>
      <div className="bg-[#f7f5f2] min-h-screen font-sans">
      {/* Navbar */}
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

  {!isLoggedIn ? (
    <>
      <button
        onClick={() => navigate("/login")}
        className="hidden md:block font-medium cursor-pointer"
      >
        Login
      </button>

      <button
        onClick={() => navigate('/signup')}
        className="cursor-pointer bg-green-900 text-white px-5 py-3 rounded-xl font-semibold hover:bg-green-800 transition"
      >
        Sign Up
      </button>
    </>
  ) : (
    <div className="flex items-center gap-4">

        <CartIcon />

      <UserCircle2
        size={30}
        className="text-green-900"
        // onClick={() => navigate("/profile")}
      />

      <button
        onClick={() => {
          localStorage.removeItem("auth");
          navigate("/login");
        }}
        className="cursor-pointer bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-xl"
      >
        Logout
      </button>

    </div>
  )}

</div>
      </nav>

      {/* Hero Section */}
      <section className="relative">
        <div
          className="h-700px bg-cover bg-center flex items-center"
          style={{
            backgroundImage:
              "url('https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?q=80&w=1600&auto=format&fit=crop')",
          }}
        >
          <div className="absolute inset-0 bg-white/50"></div>

          <div className="relative z-10 px-6 md:px-12 max-w-3xl">
            <p className="text-green-900 font-semibold text-xl mb-5">
              Find Your Perfect Stay
            </p>

            <h1 className="text-5xl md:text-7xl font-bold leading-tight text-black">
              Comfortable stays.
              <br />
              <span className="text-green-900">
                Memorable experiences.
              </span>
            </h1>

            <p className="mt-8 text-xl text-gray-700 leading-relaxed">
              Discover handpicked hotels and guesthouses that make every
              journey unforgettable.
            </p>
          </div>
        </div>

        {/* Search Box */}
        {/* <div className="relative -mt-20 z-20 px-6 md:px-12">
          <div className="bg-white rounded-3xl shadow-2xl p-5 grid grid-cols-1 md:grid-cols-5 gap-5 items-center">
            <div className="flex items-center gap-4 border-r border-gray-200 pr-4">
              <Search className="text-gray-500" />
              <div>
                <h3 className="font-semibold">Destination</h3>
                <p className="text-gray-500 text-sm">
                  Where are you going?
                </p>
              </div>
            </div>

            <div className="border-r border-gray-200 pr-4">
              <h3 className="font-semibold">Check in</h3>
              <p className="text-gray-500 text-sm">Add dates</p>
            </div>

            <div className="border-r border-gray-200 pr-4">
              <h3 className="font-semibold">Check out</h3>
              <p className="text-gray-500 text-sm">Add dates</p>
            </div>

            <div>
              <h3 className="font-semibold">Guests</h3>
              <p className="text-gray-500 text-sm">Add guests</p>
            </div>

            <button className="bg-green-900 hover:bg-green-800 transition text-white py-5 rounded-2xl text-lg font-semibold">
              Search
            </button>
          </div>
        </div> */}
      </section>

      {/* Hotels Section */}
      <section className="px-6 md:px-12 py-24">
        <div className="flex items-center justify-between mb-12">
          <div>
            <p className="text-green-900 font-semibold text-lg mb-3">
              Stay Your Way
            </p>

            <h2 className="text-5xl font-bold">
              Hotels & Guesthouses
            </h2>
          </div>

          <button onClick={() => navigate('/stays')} className="bg-white px-6 py-4 rounded-2xl shadow-md font-semibold hover:bg-gray-100">
            View all
          </button>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-8">
          {hotels.map((hotel, index) => (
            <div
              key={index}
              className="bg-white rounded-3xl overflow-hidden shadow-md hover:shadow-2xl transition duration-300"
            >
              <div className="relative">
                <img
                  src={hotel.image}
                  alt={hotel.name}
                  className="h-72 w-full object-cover"
                />

                {/* <button className="absolute top-4 right-4 bg-white/80 backdrop-blur-md p-2 rounded-full">
                  <Heart size={22} className="text-gray-700" />
                </button> */}
              </div>

              <div className="p-6">
                <h3 className="text-2xl font-bold mb-2">
                  {hotel.name}
                </h3>

                <p className="text-gray-500 mb-5">{hotel.location}</p>

                <div className="flex items-center gap-2 text-lg">
                  <Star
                    size={18}
                    fill="#facc15"
                    className="text-yellow-400"
                  />
                  <span>{hotel.rating}</span>

                  <span className="text-gray-400">•</span>

                  <span>{hotel.price} / night</span>
                </div>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Services */}
      <section className="bg-white py-24 px-6 md:px-12">
        <h2 className="text-5xl font-bold mb-16">Our Services</h2>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-10">
          {services.map((service, index) => (
            <div
              key={index}
              className="flex flex-col items-start gap-5"
            >
              <div className="text-green-900">{service.icon}</div>

              <h3 className="text-2xl font-semibold">
                {service.title}
              </h3>

              <p className="text-gray-500">{service.subtitle}</p>
            </div>
          ))}
        </div>
      </section>
    </div>
    </>
  )
}

export default HomePage;

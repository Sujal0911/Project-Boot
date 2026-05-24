import React from "react";
import { MapPin, Star, Heart } from "lucide-react";

function HotelCard({ hotel, addToCart }) {
  return (
    <div className="
  bg-white
  rounded-3xl
  overflow-hidden
  shadow-md
  hover:shadow-2xl
  transition
  duration-300
  flex
  flex-col
  h-full
">
      
      {/* IMAGE */}
      <div className="relative">
        <img
          src={hotel.image}
          alt={hotel.name}
          className="w-full h-[25vh] object-cover"
        />

        {/* FAVORITE */}
        {/* <button className="absolute top-4 right-4 bg-white/80 backdrop-blur-md p-2 rounded-full">
          <Heart size={22} className="text-gray-700" />
        </button> */}
      </div>

      {/* CONTENT */}
      <div className="p-6 flex flex-col flex-1">
        
        {/* TITLE */}
        <div className="">
          <h2 className="text-3xl font-bold text-gray-900">
            {hotel.name}
          </h2>

          <div className="flex items-center gap-2 text-gray-500 mt-2">
            <MapPin size={18} />

            <span className="text-base">
              {hotel.location}
            </span>
          </div>
        </div>

        {/* DESCRIPTION */}
          <p className="text-gray-500 text-sm leading-relaxed mb-4 min-h-[60px]">
            {hotel.description}
          </p>
          
        {/* FOOTER */}
<div className="flex items-center gap-2 text-lg mt-auto">
  <Star
    size={18}
    fill="#facc15"
    className="text-yellow-400"
  />

  <span>{hotel.rating}</span>

  <span className="text-gray-400">•</span>

  <span>{hotel.price} / night</span>
</div>

        <button
          onClick={() => addToCart(hotel.id)}
          className="cursor-pointer w-full mt-4 bg-green-900 hover:bg-green-800 text-white py-3 rounded-2xl font-semibold transition"
        >
          Add To Cart
        </button>

        {/* BUTTON
        <button className="w-full mt-6 bg-green-900 hover:bg-green-800 transition text-white py-4 rounded-2xl text-lg font-semibold">
          View Details
        </button> */}
      </div>
    </div>
  );
}

export default HotelCard;
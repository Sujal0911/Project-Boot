import { ShoppingCart } from "lucide-react";
import { useNavigate } from "react-router-dom";

function CartButton() {

  const navigate = useNavigate();

  return (
    <button
      onClick={() => navigate("/cart")}
      className="relative cursor-pointer p-3 rounded-full hover:bg-gray-100 transition"
    >

      <ShoppingCart
        size={20}
        className="text-green-900"
      />

      {/* CART COUNT */}
      {/* <span
        className="
          absolute
          -top-1
          -right-1
          bg-red-500
          text-white
          text-xs
          w-5
          h-5
          rounded-full
          flex
          items-center
          justify-center
        "
      >
        2
      </span> */}

    </button>
  );
}

export default CartButton;
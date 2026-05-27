import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import HotelCard from "../components/HotelCard";
import CartIcon from "../components/CartIcon";
import { UserCircle2 } from "lucide-react";
import Popup from "../components/Popup";

function ExperiencesPage() {

  const isLoggedIn = localStorage.getItem("auth");

  const navigate = useNavigate();

  const [city, setCity] = useState("");

  const [products, setProducts] = useState([]);

  const [loading, setLoading] = useState(true);

  const [page, setPage] = useState(0);

  const [totalPages, setTotalPages] = useState(0);

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

  // =========================================
  // FETCH PRODUCTS
  // =========================================
  useEffect(() => {
    fetchProducts();
  }, [page]);

  const fetchProducts = async () => {

    setLoading(true);

    try {

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        `http://localhost:8080/customer-user/all-experiences?page=${page}&size=12`,
        {
          method: "GET",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      const data = await response.json();

      console.log(data);

      setProducts(data.content);

      setTotalPages(data.totalPages);

    } catch (error) {

      console.log(error);

      alert("Failed to load stays");

    } finally {

      setLoading(false);

    }
  };

    // =========================================
  // SEARCH HOTELS
  // =========================================
  const searchHotels = async () => {

    try {

      setLoading(true);

      setPage(0);

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        `http://localhost:8080/customer-user/search-experiences?city=${city}&page=0&size=12`,
        {
          method: "GET",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      const data = await response.json();

      console.log(data);

      setProducts(data.content || []);

      setTotalPages(data.totalPages || 0);

    } catch (error) {

      console.log(error);

      alert("Search failed");

    } finally {

      setLoading(false);

    }
  };

  // =========================================
  // ADD TO CART
  // =========================================
  const addToCart = async (productId) => {

    try {

      const auth = localStorage.getItem("auth");

      const response = await fetch(
        `http://localhost:8080/cart/add-to-cart?productId=${productId}`,
        {
          method: "POST",

          headers: {
            Authorization: "Basic " + auth,
          },
        }
      );

      if (response.ok) {

        showPopup("Added To Cart");

      } else {

        showPopup("Failed To Add");

      }

    } catch (error) {

      console.log(error);

      alert("Server Error");

    }
  };

  return (

    <div className="min-h-screen bg-[#f7f7f7]">

      <Popup
  show={popup.show}
  message={popup.message}
  type={popup.type}
/>

      {/* ========================================= */}
      {/* NAVBAR */}
      {/* ========================================= */}

      <div className="bg-white px-8 py-5 shadow-sm flex items-center justify-between">

        {/* LOGO */}
        <div
          onClick={() => navigate("/home")}
          className="flex items-center gap-3 cursor-pointer"
        >

          <div className="text-green-900 text-4xl font-bold">
            ⌂
          </div>

          <h1 className="text-3xl font-bold">
            StayVista
          </h1>

        </div>

        {/* MENU */}
        <div className="hidden lg:flex items-center gap-10 text-lg font-medium">

          <span
            onClick={() => navigate("/home")}
            className="cursor-pointer hover:text-green-800"
          >
            Home
          </span>

          <span
              onClick={() => navigate("/stays")}
              className="hover:text-green-800 cursor-pointer"
            >
              Stays
            </span>

          <span
          onClick={() => navigate("/services")} className="cursor-pointer hover:text-green-800">
            Services
          </span>

          {/* <span 
          onClick={() => navigate("/experiences")} className="cursor-pointer hover:text-green-800">
            Experiences
          </span> */}

        </div>

        {/* RIGHT */}
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
      </div>

      {/* ========================================= */}
      {/* SEARCH */}
      {/* ========================================= */}

      <div className="px-8 mt-8">

        <div className="bg-white rounded-3xl shadow-lg p-5 flex items-center gap-4">

          <input
            type="text"
            placeholder="Search by city..."
            value={city}
            onChange={(e) => setCity(e.target.value)}
            className="flex-1 outline-none text-lg"
          />

          <button
            onClick={searchHotels}
            className="cursor-pointer bg-green-900 text-white px-6 py-3 rounded-2xl"
          >
            Search
          </button>

          <button
            onClick={fetchProducts}
            className="cursor-pointer bg-gray-200 px-6 py-3 rounded-2xl"
          >
            All
          </button>

        </div>
      </div>

      {/* ========================================= */}
      {/* HERO */}
      {/* ========================================= */}

      <div className="px-8 py-10">

        <div
          className="h-[350px] rounded-[35px] bg-cover bg-center flex items-center px-12 relative overflow-hidden"
          style={{
            backgroundImage:
              "url('https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?q=80&w=1600&auto=format&fit=crop')",
          }}
        >

          {/* OVERLAY */}
          <div className="absolute inset-0 bg-black/40"></div>

          {/* CONTENT */}
          <div className="relative z-10 max-w-3xl text-white">

            <p className="text-xl mb-4 font-medium">
              Discover Variety of Experiences
            </p>

            <h1 className="text-6xl font-bold leading-tight mb-5">
              Adventures &
              <br />
              Trek's
            </h1>

            <p className="text-xl text-gray-200">
              Find comfortable stays and unforgettable experiences
              across India.
            </p>

          </div>
        </div>
      </div>

      {/* ========================================= */}
      {/* PRODUCTS SECTION */}
      {/* ========================================= */}

      <div className="px-8 pb-14">

        {/* TITLE */}
        <div className="flex items-center justify-between mb-10">

          <div>

            <p className="text-green-900 font-semibold mb-2">
              Available Experiences
            </p>

            {/* <h2 className="text-5xl font-bold">
              Explore
            </h2> */}

          </div>

          <p className="text-gray-500 text-lg">
            {products.length} Experiences available
          </p>

        </div>

        {/* LOADING */}
        {loading ? (

          <div className="text-center py-20 text-3xl font-semibold">
            Loading Experiences...
          </div>

        ) : (

          <>
            {/* GRID */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">

              {products.map((hotel) => (

                <HotelCard
                  key={hotel.id}
                  hotel={{
                    id: hotel.id,
                    name: hotel.productName,
                    location: "India",
                    description: hotel.description,
                    rating: "4.8",
                    price: hotel.price,
                    image:hotel.imageUrl,
                  }}
                  addToCart={addToCart}
                />

              ))}

            </div>

            {/* PAGINATION */}
            <div className="flex justify-center items-center gap-5 mt-14">

              <button
                disabled={page === 0}
                onClick={() => setPage(page - 1)}
                className="bg-green-900 text-white px-6 py-3 rounded-xl disabled:bg-gray-400"
              >
                Previous
              </button>

              <span className="text-xl font-semibold">
                Page {page + 1} of {totalPages}
              </span>

              <button
                disabled={page + 1 >= totalPages}
                onClick={() => setPage(page + 1)}
                className="bg-green-900 text-white px-6 py-3 rounded-xl disabled:bg-gray-400"
              >
                Next
              </button>

            </div>
          </>

        )}
      </div>
    </div>
  );
}

  export default ExperiencesPage;
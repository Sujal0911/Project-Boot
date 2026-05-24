import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from './pages/HomePage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import SignUpPage from './pages/SignUpPage.jsx'
import CartPage from './pages/Cart.jsx'
import OrderHistoryPage from './pages/OrderHistory.jsx';
import Stays from './pages/Stays.jsx';
import Services from './pages/Services.jsx'
import Experiences from './pages/Experiences.jsx';

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
  <Routes>
    <Route path='/' element={<App />}></Route>
    <Route path='/home' element={<HomePage />}></Route>
    <Route path='/login' element={<LoginPage />}></Route>
    <Route path='/signup' element={<SignUpPage />}></Route>
    <Route path='/cart' element={<CartPage />}></Route>
    <Route path='/orders' element={<OrderHistoryPage />}></Route>
    <Route path='/stays' element={<Stays />}></Route>
    <Route path='/services' element={<Services />}></Route>
    <Route path='/experiences' element={<Experiences />}></Route>
  </Routes>
  </BrowserRouter>
)

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import Home from './pages/HomePage/Home'
import LoginPage from './pages/LoginPage/LoginPage'
import RegisterPage from './pages/RegisterPage/RegisterPage'
import ChatPage from './pages/ChatsPage/ChatPage'
import ChatsHomePage from './pages/ChatsPage/ChatsHomePage'

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/chats" element={<ChatPage />} />
          <Route path="/chats/chatId" element={<ChatPage />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App

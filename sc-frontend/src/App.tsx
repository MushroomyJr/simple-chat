import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import Home from './pages/HomePage/Home'
import LoginPage from './pages/LoginPage/LoginPage'
import RegisterPage from './pages/RegisterPage/RegisterPage'
import ChatPage from './pages/ChatsPage/ChatPage'
import { ChatProvider } from './context/ChatContext'

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/chats" element={
            <ChatProvider>
              <ChatPage />
            </ChatProvider>
          } 
          />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App

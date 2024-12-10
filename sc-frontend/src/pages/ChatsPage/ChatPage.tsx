import { Navigate, useNavigate } from 'react-router'
import ChatWindow from '../../components/ChatWindow'
import SideBar from '../../components/SideBar'
import UserMenu from '../../components/UserMenu'
import './ChatPage.css'
import axios from 'axios'
import { useState } from 'react'

const ChatPage = () => {
  const [chatInUse, setChatInUse] = useState<number>(-1)
  const jwtForTesting =
    'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXN0YWZhLW5ld2VyIiwiaWF0IjoxNzMzNzQwMDU2LCJleHAiOjE3MzM4MjY0NTYsImlzcyI6InNpbXBsZV9jaGF0In0.FUFAgVYchjm6Ip8oaTeSaNc5aKHhhXyIzks6BfJifV1z52k5e4Iy-X89VKoVZ7LYWLF-SL1caenl9odC62qwrQ'
  const handleChatSelection = async (chatId: any) => {
    console.log('from chat page, ', chatId)
    const response = await axios.get(
      `http://localhost:8080/api/chat/${chatId}`,
      {
        headers: {
          Authorization: `Bearer ${jwtForTesting}`,
        },
      }
    )
    console.log(response.data)
    setChatInUse(chatId)
  }
  return (
    <>
      <div className="chats-page">
        <SideBar handleChatSelection={handleChatSelection} />
        <div className="chat-main">
          <UserMenu />
          <ChatWindow selectedChatId={chatInUse} />
        </div>
      </div>
    </>
  )
}

export default ChatPage

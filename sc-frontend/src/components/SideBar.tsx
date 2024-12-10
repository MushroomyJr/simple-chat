import { Button } from '@mui/material'
import './SideBar.css'
import axios from 'axios'
import { useEffect, useState } from 'react'

const SideBar = ({ handleChatSelection }: any) => {
  const jwtForTesting =
    'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXN0YWZhLW5ld2VyIiwiaWF0IjoxNzMzNzQwMDU2LCJleHAiOjE3MzM4MjY0NTYsImlzcyI6InNpbXBsZV9jaGF0In0.FUFAgVYchjm6Ip8oaTeSaNc5aKHhhXyIzks6BfJifV1z52k5e4Iy-X89VKoVZ7LYWLF-SL1caenl9odC62qwrQ'

  const [chats, setChats] = useState([])
  useEffect(() => {
    const fetchChats = async () => {
      try {
        const token = localStorage.getItem('jwt')
        const response = await axios.get('http://localhost:8080/api/chat/1', {
          headers: { Authorization: `Bearer ${jwtForTesting}` },
        })
        setChats(response.data)
        console.log(response)
      } catch {}
    }
  }, [])

  const mock_data = [
    { id: 1, name: 'Chat one!' },
    { id: 2, name: 'Chat two!' },
    { id: 3, name: 'Chat three!' },
  ]

  const handleCreateChat = () => {
    alert('creating new chat')
  }

  const handleChatSelect = (chatId: number) => {
    alert('chat has been selected, chatId: ' + chatId)
    handleChatSelection(chatId)
  }

  return (
    <>
      <div className="sidebar">
        <h3 className="title">Simple Chat</h3>
        <Button onClick={handleCreateChat}>Create Chat 📝</Button>

        <ul className="user-chats">
          {mock_data.map((chat) => (
            <li
              className="chat-item"
              key={chat.id}
              onClick={() => handleChatSelect(chat.id)}
            >
              {chat.name}
            </li>
          ))}
        </ul>
      </div>
    </>
  )
}

export default SideBar

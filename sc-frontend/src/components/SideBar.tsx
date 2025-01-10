import { useEffect, useState } from 'react'
import axios from 'axios'
import { Chat } from '../common/types'
import Button from '@mui/material/Button'
import './SideBar.css'
const Sidebar = ({ onSelectChat }: any) => {
  const [chats, setChats] = useState([])
  const userId = localStorage.getItem('userId')
  const jwt = localStorage.getItem('jwt')
  const user_id = localStorage.getItem('user_id')
  useEffect(() => {
    const fetchChats = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/chat/user/${user_id}`,
          {
            headers: { Authorization: `Bearer ${jwt}` },
          }
        )
        console.log('response: ', response.data)
        setChats(response.data)
      } catch (error) {
        console.error('Failed to fetch chats:', error)
      }
    }

    fetchChats()
  }, [userId])

  const handleCreateNewChat = () => {
    alert('creating new chat')
  }
  return (
    <>
      <div className="sidebar">
        <h3 className="title">Simple Chat</h3>
        <Button className="create-chat-button" onClick={handleCreateNewChat}>
          new chat
        </Button>
        <ul className="user-chats">
          {chats.map((chat: Chat) => (
            <li
              key={chat.id}
              onClick={() => onSelectChat({ id: chat.id, name: chat.name })}
              className="chat-item"
            >
              {chat.name}
            </li>
          ))}
        </ul>
      </div>
    </>
  )
}

export default Sidebar

import { useEffect, useState } from 'react'
import axios from 'axios'
import { Chat } from '../common/types'
import Button from '@mui/material/Button'
import './SideBar.css'
import config from '../pages/config'
import NewChatModal from './NewChatModal'
const Sidebar = ({ onSelectChat }: any) => {
  const [isModalOpen, setModalOpen] = useState(false)
  const [chats, setChats] = useState([])
  const userId = localStorage.getItem('userId')
  const jwt = localStorage.getItem('jwt')
  const user_id = localStorage.getItem('user_id')
  useEffect(() => {
    const fetchChats = async () => {
      try {
        const response = await axios.get(
          `${config.API_BASE_URL}/api/chat/user/${user_id}`,
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

  return (
    <>
      <div className="sidebar">
        <h3 className="title">Simple Chat</h3>
        <Button className="create-chat-button" onClick={()=>setModalOpen(true)}>
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
        <NewChatModal isOpen={isModalOpen} onClose={()=>setModalOpen(false)}/>
      </div>
    </>
  )
}

export default Sidebar

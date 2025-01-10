import { useEffect, useState } from 'react'
import axios from 'axios'
import { Message } from '../common/types'
import './ChatWindow.css'
type ChatWindowProps = {
  chatId: number
  chatName: string
}
const ChatWindow = ({ chatId, chatName }: ChatWindowProps) => {
  const [messages, setMessages] = useState([])
  const currentUser = localStorage.getItem('user_id')
  const jwt = localStorage.getItem('jwt')
  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/chat/messages/${chatId}`,
          { headers: { Authorization: `Bearer ${jwt}` } }
        )
        console.log(response.data)
        setMessages(response.data)
      } catch (error) {
        console.error('Failed to fetch messages:', error)
      }
    }

    const fetchChatParticipants = async () => {}

    fetchMessages()
  }, [chatId])

  return (
    <div className="chat-window">
      <h2>Messages</h2>
      <ul className="message-list">
        <li className="chat-name">
          <strong>{chatName}</strong>
        </li>
        {messages.map((message: Message) => (
          <li
            key={message.id}
            className={`message-bubble ${
              currentUser != null && message.senderId === parseInt(currentUser)
                ? 'self'
                : 'other'
            }`}
          >
            <div className="message-sender">{message.senderId}:</div>
            <div className="message-content">{message.content}</div>
          </li>
        ))}
      </ul>
      <div className="messageTextBox">
        <input type="text" />
        <button>Send</button>
      </div>
    </div>
  )
}

export default ChatWindow

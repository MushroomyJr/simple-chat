import { useEffect, useState } from 'react'
import axios from 'axios'
import { Message } from '../common/types'
import './ChatWindow.css'
import config from '../pages/config'
type ChatWindowProps = {
  chatId: number
  chatName: string
}
const ChatWindow = ({ chatId, chatName }: ChatWindowProps) => {
  const [messages, setMessages] = useState<Message[]>([])
  const [currentMessage, setCurrentMessage] = useState('')
  const currentUser = localStorage.getItem('user_id')
  const jwt = localStorage.getItem('jwt')
  const currentName = localStorage.getItem('user_name')
  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const response = await axios.get(
          `${config.API_BASE_URL}/api/chat/messages/${chatId}`,
          { headers: { Authorization: `Bearer ${jwt}` } }
        )
        console.log(response.data)
        setMessages(response.data)
      } catch (error) {
        console.error('Failed to fetch messages:', error)
      }
    }
    fetchMessages()
  }, [chatId])

  //sending a message we will have a state for it, when clicking 
  //we also wanna track the changes being made to the input message
  const messageChange = (event : React.ChangeEvent<HTMLInputElement>)=>{
    setCurrentMessage(event.target.value)
  }
  const sendMessage = async (event : React.FormEvent<HTMLFormElement>)=>{
    event.preventDefault();
    try{
      const response = await axios({
        method : 'post',
        url : `${config.API_BASE_URL}/api/chat/${chatId}/addMessage`,
        headers : { Authorization : `Bearer ${jwt}`, "Content-Type": "application/json"},
        data : {
          senderId: currentUser,
          content: currentMessage
        }
      })

      if(response.status === 200){
        console.log('message sent!', response)
        const newMessage = {
          senderId : parseInt(currentUser!),
          content : currentMessage
        }
        setMessages([...messages,newMessage])
        setCurrentMessage('')

      }
    }catch(error){
      console.log('there was an error sending the message: ', error)
    }
  }
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
            <div className="message-sender">{message.senderId===parseInt(currentUser!) ? currentName : 'other_user'}:</div>
            <div className="message-content">{message.content}</div>
          </li>
        ))}
      </ul>
      <div className="messageTextBox">
        <form onSubmit={sendMessage}>
          <input type="text" value={currentMessage} onChange={messageChange}/>
          <button type='submit'>Send</button>
        </form>
      </div>
    </div>
  )
}

export default ChatWindow

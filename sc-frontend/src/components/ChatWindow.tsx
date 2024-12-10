import { Button } from '@mui/material'
import { useEffect, useState } from 'react'
import './ChatWindow.css'
const ChatWindow = ({ selectedChatId }: { selectedChatId: number }) => {
  const mock_messages = [
    { id: 1, sender: 'mustafa', content: 'this is message one' },
    { id: 2, sender: 'saleha', content: 'this is the second message' },
    {
      id: 3,
      sender: 'mustafa',
      content: 'this is the second message from first user third in chat',
    },
  ]
  const [newMessage, setNewMessage] = useState('')
  const handleMessageSend = () => {
    if (!newMessage.trim) return
    else {
      console.log('new message sent', newMessage)
    }
  }

  useEffect(() => {
    const fetchMessages = () => {}
  }, [selectedChatId])
  return (
    <>
      {selectedChatId != null ? (
        <>
          <div className="chat-window">
            <div className="message-list">
              {mock_messages.map((message) => (
                <div
                  key={message.id}
                  className={`message-bubble ${
                    message.sender === 'mustafa' ? 'self' : 'other'
                  }`}
                >
                  {message.content}
                </div>
              ))}
            </div>
            <div className="message-input-container">
              <input
                type="text"
                value={newMessage}
                onChange={(e) => {
                  setNewMessage(e.target.value)
                }}
                onKeyDown={(e) => {
                  if (e.key == 'Enter') handleMessageSend()
                }}
                placeholder="Type a new message..."
                className="message-input"
              />
              <Button onClick={handleMessageSend} className="send-button">
                Send
              </Button>
            </div>
          </div>
        </>
      ) : (
        <div>no chat selected</div>
      )}
    </>
  )
}

export default ChatWindow

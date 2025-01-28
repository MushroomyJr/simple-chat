import ChatWindow from '../../components/ChatWindow'
import Sidebar from '../../components/SideBar'
import './ChatPage.css'
import UserMenu from '../../components/UserMenu'
import { useChat } from '../../context/ChatContext'
import { selectedChat } from '../../common/types'

const ChatPage = () => {
  const {selectedChat, setSelectedChat} = useChat();
  const handleChatSelect = (response: selectedChat) => {
    setSelectedChat(response);
  }
  return (
    <>
      <div className="chats-page">
        <Sidebar onSelectChat={handleChatSelect} />
        <div className="chat-main">
          <UserMenu />
          {selectedChat ? (
            <ChatWindow chatId={selectedChat.id} chatName={selectedChat.name} />
          ) : (
            <p>Select a chat to view messages.</p>
          )}
        </div>
      </div>
    </>
  )
}

export default ChatPage

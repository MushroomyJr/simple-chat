import { createContext, ReactNode, useContext, useState } from "react";
import {selectedChat} from '../common/types'

type ChatContextType = {
    selectedChat : selectedChat | null;
    setSelectedChat: (chat: selectedChat)=>void | null;
}
const ChatContext = createContext<ChatContextType | undefined>(undefined);

export const ChatProvider = ({ children } : {children: ReactNode}) => {
  const [selectedChat, setSelectedChat] = useState<selectedChat | null>(null);
  return (
    <ChatContext.Provider value={{ selectedChat, setSelectedChat }}>
      {children}
    </ChatContext.Provider>
  );
};

export const useChat = () => {
    const context = useContext(ChatContext);
    if (!context) {
      throw new Error("useChat must be used within a ChatProvider");
    }
    return context;
};

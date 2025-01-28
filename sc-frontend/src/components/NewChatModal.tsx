import React, { useState } from 'react';
import Modal from 'react-modal';
import axios from 'axios';
import config from '../pages/config';
import './NewChatModal.css'

Modal.setAppElement('#root'); // Needed for accessibility

type NewChatModalProps = {
  isOpen: boolean;
  onClose: () => void;
};

const NewChatModal: React.FC<NewChatModalProps> = ({ isOpen, onClose }) => {
    const jwt = localStorage.getItem('jwt');
    const currentUser = localStorage.getItem('user_name')
    const [chatName, setChatName] = useState('');
    const [participants, setParticipants] = useState<string[]>([currentUser!]);
  

  // Handle input changes
  const handleChatNameChange = (e: React.ChangeEvent<HTMLInputElement>) => setChatName(e.target.value);
  const handleParticipantChange = (index: number, value: string) => {
    const newParticipants = [...participants];
    newParticipants[index] = value;
    setParticipants(newParticipants);
  };

  // Add a new participant input field
  const addParticipantField = () => setParticipants([...participants, '']);

  // Remove a participant input field
  const removeParticipantField = (index: number) => {
    setParticipants(participants.filter((_, i) => i !== index));
  };

  // Submit new chat
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!chatName.trim() || participants.some(p => !p.trim())) {
      alert('Please fill out all fields.');
      return;
    }

    try {
      const response = await axios.post(
        `${config.API_BASE_URL}/api/chat/create`,
        {
          name: chatName,
          participantNames: participants, // Expecting backend to process participant usernames
        },
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
            'Content-Type': 'application/json',
          },
        }
      );

      if (response.status === 200) {
        console.log('Chat created successfully:', response.data);
        onClose(); // Close modal on success
      }
    } catch (error) {
      console.error('Error creating chat:', error);
      alert('Failed to create chat.');
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onClose}
      contentLabel="Create New Chat"
      className="modal-content"
      overlayClassName="modal-overlay"
    >
      <h2>Create New Chat</h2>
      <form onSubmit={handleSubmit}>
        <label>Chat Name:</label>
        <input type="text" value={chatName} onChange={handleChatNameChange} required />

        <label>Participants:</label>
        {participants.map((participant, index) => (
          <div key={index} className="participant-input">
            <input
              type="text"
              value={participant}
              onChange={(e) => handleParticipantChange(index, e.target.value)}
              placeholder="Enter username"
              required
            />
            {index > 0 && (
              <button type="button" onClick={() => removeParticipantField(index)}>❌</button>
            )}
          </div>
        ))}

        <button type="button" onClick={addParticipantField}>➕ Add Participant</button>

        <div className="modal-actions">
          <button type="submit">Create Chat</button>
          <button type="button" onClick={onClose}>Cancel</button>
        </div>
      </form>
    </Modal>
  );
};

export default NewChatModal;

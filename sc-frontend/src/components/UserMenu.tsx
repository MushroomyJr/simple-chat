import { Button } from '@mui/material'
import './UserMenu.css'
import axios from 'axios'
import { useNavigate } from 'react-router'
const UserMenu = () => {
  const navigate = useNavigate()
  const handleLogout = () => {
    alert('user is now logged out')
    localStorage.removeItem('user_id')
    localStorage.removeItem('jwt')
    navigate('/')
  }
  const jwt = localStorage.getItem('jwt')
  const user_id = localStorage.getItem('user_id')

  return (
    <>
      <div className="user-menu">
        <h4> Welcome, {user_id}</h4>
        <Button className="logout-button" onClick={handleLogout}>
          log out
        </Button>
      </div>
    </>
  )
}

export default UserMenu

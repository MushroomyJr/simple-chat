import { Button } from '@mui/material'
import './UserMenu.css'
import { useNavigate } from 'react-router'
const UserMenu = () => {
  const navigate = useNavigate()
  const handleLogout = () => {
    alert('user is now logged out')
    localStorage.removeItem('user_id')
    localStorage.removeItem('jwt')
    localStorage.removeItem('user_name')
    navigate('/')
  }
  const user_name = localStorage.getItem('user_name')

  return (
    <>
      <div className="user-menu">
        <h4> Welcome, {user_name}</h4>
        <Button className="logout-button" onClick={handleLogout}>
          log out
        </Button>
      </div>
    </>
  )
}

export default UserMenu

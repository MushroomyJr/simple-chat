import { Button } from '@mui/material'
import './UserMenu.css'
const UserMenu = () => {
  const handleLogout = () => {
    alert('user is now logged out')
  }
  const user = 'mustafa'
  return (
    <>
      <div className="user-menu">
        <h4> Welcome, {user}</h4>
        <Button className="logout-button" onClick={handleLogout}>
          log out
        </Button>
      </div>
    </>
  )
}

export default UserMenu

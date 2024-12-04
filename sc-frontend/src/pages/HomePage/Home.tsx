import { Button } from '@mui/material'
import { Route } from 'react-router'
import LoginPage from '../LoginPage/LoginPage'
import NavigateButton from '../../components/NavigateButton'
const Home = () => {
  const changePage = () => {
    return <Route path="/login" element={<LoginPage />} />
  }
  return (
    <>
      <h1>Simple Chat</h1>
      <p>the simple messaging app 💬</p>
      <div className="buttons">
        <NavigateButton
          to="/login"
          text="login"
          state={{ referrer: 'HomePage' }}
        />
        <NavigateButton
          to="/register"
          text="register"
          state={{ referrer: 'HomePage' }}
        />
      </div>
    </>
  )
}

export default Home

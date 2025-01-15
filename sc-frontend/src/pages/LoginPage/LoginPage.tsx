import axios from 'axios'
import LoginForm from '../../components/LoginForm'
import { useNavigate } from 'react-router'
import { useState } from 'react'
import config from '../config'

type LoginInfo = {
  username: string
  password: string
}

const LoginPage = () => {
  const navigate = useNavigate()
  const [error, setError] = useState('')
  const handleLogin = async (loginInfo: LoginInfo) => {
    console.log(loginInfo)
    const respone = await axios.post(
      `${config.API_BASE_URL}/api/auth/login`,
      loginInfo,{
        headers:{
          "Content-Type": "application/json",
        }
      }
    )

    if (respone.status === 200) {
      console.log('user logged in')
      localStorage.setItem('jwt', respone.data.jwtToken)
      localStorage.setItem('user_id', respone.data.user_id)
    } else {
      setError('error loggin in user')
    }
    navigate('/chats')
  }
  return (
    <>
      <h1>the login page.</h1>
      <LoginForm handleLogin={handleLogin} />
    </>
  )
}

export default LoginPage

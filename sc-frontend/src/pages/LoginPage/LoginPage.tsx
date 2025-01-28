import axios from 'axios'
import LoginForm from '../../components/LoginForm'
import { useNavigate } from 'react-router'
import { useState } from 'react'
import config from '../config'

type LoginInfo = {
  userName: string
  password: string
}

const LoginPage = () => {
  const navigate = useNavigate()
  const [error, setError] = useState('')
  const handleLogin = async (loginInfo: LoginInfo) => {
    const response = await axios.post(
      `${config.API_BASE_URL}/api/auth/login`,
      loginInfo,{
        headers:{
          "Content-Type": "application/json",
        }
      }
    )

    if (response.status === 200) {
      console.log('user logged in')
      console.log('log in response: ', response)
      console.log('login info', loginInfo)
      localStorage.setItem('jwt', response.data.jwtToken)
      localStorage.setItem('user_id', response.data.user_id)
      localStorage.setItem('user_name', loginInfo.userName)
    } else {
      setError('error loggin in user')
    }
    navigate('/chats')
  }
  return (
    <>
      <h1>the login page.</h1>
      <LoginForm handleLogin={handleLogin} />
      {error ? <>there was an error</> : <></>}
    </>
  )
}

export default LoginPage

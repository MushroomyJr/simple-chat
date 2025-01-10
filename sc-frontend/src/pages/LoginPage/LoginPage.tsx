import axios from 'axios'
import LoginForm from '../../components/LoginForm'
import { useNavigate } from 'react-router'
import { useState } from 'react'

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
      'http://localhost:8080/api/auth/login',
      loginInfo
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

import { useState } from 'react'
import axios from 'axios'
import RegistrationForm from '../../components/RegistrationForm'
import { UserInfo } from '../../common/types'
import NavigateButton from '../../components/NavigateButton'
import './RegisterPage.css'
import { useNavigate } from 'react-router'

const RegisterPage = () => {
  const [error, setError] = useState()
  const navigate = useNavigate()
  const handleUserRegistration = async (userInfo: UserInfo) => {
    console.log('user info: ', userInfo)
    try {
      const response = await axios.post(
        'http://localhost:8080/api/auth/register',
        userInfo
      )

      if (response.status === 200) {
        localStorage.setItem('jwt', response.data.jwtToken)
        localStorage.setItem('user_id', response.data.user_id)
      }

      navigate('/chats')
    } catch (err: any) {
      console.log('registration failed', err.response || err.message)
      setError(
        err.response?.data?.message ||
          'failed to register, please try agin later'
      )
    }
  }
  return (
    <>
      <NavigateButton to="/" text="🏠" state={{ referrer: 'RegisterPage' }} />
      <h1>the register page.</h1>
      {error && <p className="error-message">{error}</p>}
      <RegistrationForm handleRegistration={handleUserRegistration} />
    </>
  )
}

export default RegisterPage

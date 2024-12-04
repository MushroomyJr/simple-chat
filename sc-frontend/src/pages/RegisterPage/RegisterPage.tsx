import { useState } from 'react'
import axios from 'axios'
import RegistrationForm from '../../components/RegistrationForm'
import { UserInfo } from '../../common/types'
import NavigateButton from '../../components/NavigateButton'
import './RegisterPage.css'
const RegisterPage = () => {
  const [registered, setRegistered] = useState(false)
  const [userInfo, setUserInfo] = useState<UserInfo>({})
  const handleUserRegistration = (userInfo: UserInfo) => {
    console.log('user info: ', userInfo)
    setUserInfo(userInfo)
    setRegistered(true)
  }
  return (
    <>
      <NavigateButton to="/" text="🏠" state={{ referrer: 'RegisterPage' }} />
      <h1>the register page.</h1>
      {registered ? (
        <p>
          getting you registered <strong>{userInfo.userName}</strong>
        </p>
      ) : (
        <RegistrationForm handleRegistration={handleUserRegistration} />
      )}
    </>
  )
}

export default RegisterPage

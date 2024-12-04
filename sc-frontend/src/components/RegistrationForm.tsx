import { useRef, useState } from 'react'
import { UserInfo } from '../common/types'
import { Button } from '@mui/material'

const RegistrationForm = ({ handleRegistration }: any) => {
  const [userInfo, setUserInfo] = useState<UserInfo>({})
  const [error, setError] = useState<string | null>(null)
  const confirmPassRef = useRef<HTMLInputElement>(null)

  const handleChange = (e: any, fieldChange: string) => {
    setUserInfo((prev) => {
      return { ...prev, [fieldChange]: e.target.value }
    })
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (confirmPassRef.current?.value !== userInfo.password) {
      setError('passowrds do not match')
      return
    }

    setError(null)
    handleRegistration(userInfo)
  }

  return (
    <div className="form-container">
      <form className="registration-form" onSubmit={handleSubmit}>
        <input
          type="text"
          className="input"
          placeholder="user name 👤"
          required={true}
          onChange={(e) => handleChange(e, 'userName')}
        />
        <input
          type="text"
          className="input"
          placeholder="email 📧"
          required={true}
          onChange={(e) => handleChange(e, 'email')}
        />
        <input
          type="text"
          className="input"
          placeholder="phone number 📞"
          required={false}
          onChange={(e) => handleChange(e, 'phoneNumber')}
        />
        <input
          type="password"
          className="input"
          placeholder="password 🔑"
          required={true}
          onChange={(e) => handleChange(e, 'password')}
        />
        <input
          type="password"
          className="input"
          placeholder="confirm password 🔑"
          ref={confirmPassRef}
          required={true}
        />
        {error && <p className="error-message">{error}</p>}
        <Button type="submit" className="submit-button">
          register
        </Button>
      </form>
    </div>
  )
}

export default RegistrationForm

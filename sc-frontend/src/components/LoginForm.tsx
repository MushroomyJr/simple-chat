import Button from '@mui/material/Button'
import { useState } from 'react'

type LoginFormProps = {
  handleLogin: any
}

const LoginForm = ({ handleLogin }: LoginFormProps) => {
  const [loginInfo, setLoginInfo] = useState({})

  const handleChange = (e: any, field: string) => {
    setLoginInfo((prev) => {
      return { ...prev, [field]: e.target.value }
    })
  }

  const handleSubmit = (e: any) => {
    e.preventDefault()
    handleLogin(loginInfo)
  }
  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="user name"
          onChange={(e) => handleChange(e, 'userName')}
        />
        <input
          type="password"
          placeholder="password"
          onChange={(e) => handleChange(e, 'password')}
        />
        <Button type="submit">login</Button>
      </form>
    </div>
  )
}

export default LoginForm

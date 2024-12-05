import Button from '@mui/material/Button'
import { useState } from 'react'

const LoginForm = () => {
  const [loginInfo, setLoginInfo] = useState({})
  const handleChange = (e: any, field: string) => {
    setLoginInfo((prev) => {
      return { ...prev, [field]: e.target.value }
    })
    console.log(loginInfo)
  }
  return (
    <div className="form-container">
      <form>
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

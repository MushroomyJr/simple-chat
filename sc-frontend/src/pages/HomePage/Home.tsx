import { Button } from '@mui/material'
const Home = () => {
  return (
    <>
      <h1> Simple Chat</h1>
      <p>this simple messaging app.</p>
      <div className="buttons">
        <Button onClick={() => console.log('going to take you to login page')}>
          login
        </Button>
        <Button
          onClick={() => console.log('going to take you to sign up page')}
        >
          sign up
        </Button>
      </div>
    </>
  )
}

export default Home

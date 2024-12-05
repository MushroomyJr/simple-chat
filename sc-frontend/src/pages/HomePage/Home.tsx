import NavigateButton from '../../components/NavigateButton'
const Home = () => {
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

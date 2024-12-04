import { Button } from '@mui/material'
import { useNavigate } from 'react-router'
import { NavigateButtonProps } from '../common/types'

/**
 * we are going to create a button that we can navigate to specified endpoints
 * @param NavigatorButtonProps
 * @returns
 */
const NavigateButton = ({ to, text, state }: NavigateButtonProps) => {
  const navigate = useNavigate()

  const handleClick = () => {
    navigate(to, { state })
  }

  return (
    <>
      <Button onClick={handleClick}>{text}</Button>
    </>
  )
}

export default NavigateButton

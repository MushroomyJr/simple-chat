export type UserInfo = {
  userName?: string
  email?: string
  password?: string
  phoneNumber?: number
}

/**
 * we have three props for when creating a navigation button,
 * the end point we go 'to' the 'text' shown on the button and the
 * state aka the referrer from which the button is called
 */
export type NavigateButtonProps = {
  to: string
  text: string
  state: any
}

export type Chat = {
  id: number
  size: number
  name?: string
  messages?: [Message]
}

export type Message = {
  id: number
  senderId: number
  content: string
  index: number
}

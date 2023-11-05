import { createAuth0Client, User, type Auth0Client } from "@auth0/auth0-spa-js";
import { writable, type Writable } from "svelte/store";

let user: Writable<User> = writable();
let token: Writable<string> = writable();
export function withAuth(): {
  user: Writable<User>;
  token: Writable<string>;
  login: () => Promise<void>;
  logout: () => Promise<void>;
} {
  function getClient(): Promise<Auth0Client> {
    return createAuth0Client({
      domain: "dev-qr3ba0z0ebgps6fg.us.auth0.com",
      clientId: "dBzUP6GLpqTy3E4MRB1vAciuor9lH02p",
      authorizationParams: {
        redirect_uri: "http://localhost:5173/callback",
        audience: "http://localhost:5174",
      },
    });
  }

  async function getUser(): Promise<void> {
    const client = await getClient();
    try {
      // ensure the client has a token to cal the Auth0 Authentication server.
      const accessToken = await client.getTokenSilently();
      token.set(accessToken);
      // get the client to fetch the user information.
      const userDetails = await client.getUser();
      // publish the user information
      user.set(userDetails);
    } catch (e) {
      // if the user is not logged in the getTokenSilently call will fail.
      console.warn(e);
    }
  }

  async function login(): Promise<void> {
    const client = await getClient();
    client.loginWithRedirect({});
  }
  async function logout(): Promise<void> {
    const client = await getClient();
    client.logout({});
  }

  getUser();
  return {
    login,
    logout,
    user,
    token,
  };
}
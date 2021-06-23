export const TOKEN_KEY = "@TOKEN";
export const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null;
export const getToken = () => localStorage.getItem(TOKEN_KEY);

export const login = token => { localStorage.setItem(TOKEN_KEY, token); };
export const logout = () => { 
  localStorage.removeItem(TOKEN_KEY); 
  localStorage.removeItem(NOME_DO_USUARIO)
  localStorage.removeItem(ID_USUARIO)
};

export const NOME_DO_USUARIO = "@NOME:usuario";
export const nomeUsuario = nome => {localStorage.setItem(NOME_DO_USUARIO, nome)};
export const getNomeUsuario = () => localStorage.getItem(NOME_DO_USUARIO);

export const ID_USUARIO = "@ID:usuario";
export const idUsuario = id => {localStorage.setItem(ID_USUARIO, id)};
export const getIdUsuario  = () => localStorage.getItem(ID_USUARIO);
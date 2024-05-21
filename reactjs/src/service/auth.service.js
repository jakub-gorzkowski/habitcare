import axios from "axios";

const logout = async () => {
  try {
    localStorage.removeItem("user");
    localStorage.clear();
    await axios.post("http://localhost:8080/api/auth/logout");
  } catch (error) {
    console.error("Error:", error);
  }
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  getCurrentUser,
  logout,
};

export default AuthService;

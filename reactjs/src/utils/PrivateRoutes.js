import { Outlet, Navigate } from "react-router-dom";
import AuthService from "../service/auth.service";

const PrivateRoutes = () => {
  let auth = AuthService.getCurrentUser();
  return auth ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoutes;

import React, { useState, useEffect } from 'react';
import './Profile.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import ProfileLogo from "../components/ProfileLogo.js";
import ProfileDetails from "../components/ProfileDetails.js";
import myProfile from "../assets/myProfile.svg";
import axios from 'axios';
import authHeader from '../service/auth-header.js';
function Profile() {
    const [username, setUsername] = useState('');
    const [profileImage, setProfileImage] = useState('');
    const [selectedFile, setSelectedFile] = useState(null);
    const activePage = "Profile";
    useEffect(() => {
        axios.get('http://localhost:8080/api/users/get', { headers: authHeader() })
            .then(response => {
                setUsername(response.data.username);
                // Replace double backslashes with single slashes in the imageUrl
                const imageUrl = `http://localhost:8080/api/users/images?path=${encodeURIComponent(response.data.imageUrl.replace(/\\\\/g, '/'))}`;
                setProfileImage(imageUrl);
            })
            .catch(error => {
                console.error('There was an error fetching the user data!', error);
                setProfileImage(myProfile);
            });
    }, []);
    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };
    const handleUpload = () => {
        const formData = new FormData();
        formData.append("file", selectedFile);

        axios.post('http://localhost:8080/api/users/update-image', formData, { headers: authHeader() })
            .then(response => {
                console.log('Image uploaded successfully');
                // Fetch the updated user data
                axios.get('http://localhost:8080/api/users/get', { headers: authHeader() })
                    .then(response => {
                        setUsername(response.data.username);
                        // Replace double backslashes with single slashes in the imageUrl
                        const imageUrl = `http://localhost:8080/api/users/images?path=${encodeURIComponent(response.data.imageUrl.replace(/\\\\/g, '/'))}`;
                        setProfileImage(imageUrl);
                        console.log(imageUrl);
                    })
                    .catch(error => {
                        console.error('There was an error fetching the user data!', error);
                    });
            })
            .catch(error => {
                console.error('There was an error uploading the image!', error);
            });
    };
    return (
        <div className="profile">
            <Navbar />
            <SideBar />
            <ProfileLogo />
            <div className="profile-container">
                <div className="profile-text">My Profile</div>
                <div className="profile-picture">
                    <img src={profileImage} alt="Profile Image"/>
                </div>
                <div className="username-profile">{username}</div>
                <div className="profile-details">
                    <ProfileDetails/>
                </div>
                <div className="file-all">
                    <div className="update-text">Update Photo</div>
                    <input type="file" onChange={handleFileChange}/>
                    <button className="button-update" onClick={handleUpload}>Update Photo</button>
                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage}/>
            </div>
        </div>
    );
}

export default Profile;
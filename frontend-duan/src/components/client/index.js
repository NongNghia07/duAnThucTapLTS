import React, { createContext } from "react";
import { Container } from 'react-bootstrap';

import Navbar from "./navbar";
import Footer from "./footer";
import Main from "./main";

export const ThemeContext = createContext(null);

export default function Client(params) {

    return (
        <>
            <Container fluid>
                <Navbar />
                <ThemeContext.Provider>
                    <Main />
                </ThemeContext.Provider>
                <Footer />
            </Container>
        </>
    )
}
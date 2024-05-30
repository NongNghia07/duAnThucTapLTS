import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { Card, CardBody, CardTitle, CardSubtitle, Table } from "reactstrap";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Modal from 'react-bootstrap/Modal';
import Form from "react-bootstrap/Form";
import { toast } from 'react-toastify';

const SubjectDetail = () => {
    const [subject, setSubject] = useState()


    return (
        <Card>
            {subject &&
                <CardBody>

                </CardBody>
            }
        </Card>
    );
};

export default SubjectDetail;
import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, CardBody, CardTitle, CardSubtitle, Table } from "reactstrap";
import { useNavigate } from "react-router-dom";

const Subject = () => {
    const [subject, setSubject] = useState()
    const navigate = useNavigate();

    const getApiSubject = async () => {
        const res = await axios.get(`http://localhost:8080/api/v1/subject/getAllPage`);
        const data = res.data ? res.data : []
        setSubject(data)
    }

    useEffect(() => {
        getApiSubject();
    }, [])

    const openDetail = (id) => {
        navigate(`${id}`);
    }

    return (
        <div>
            <Card>
                {subject &&
                    <Table className="no-wrap mt-3 align-middle" responsive borderless>
                        <thead>
                            <tr>
                                <th>Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            {subject.content.map(item => (
                                <tr onClick={() => openDetail(item.id)} key={item.id} className="border-top">
                                    <td>
                                        <div className="d-flex align-items-center p-2">
                                            <img
                                                src={item.symbol}
                                                className="rounded-circle"
                                                alt="avatar"
                                                width="45"
                                                height="45"
                                            />
                                            <div className="ms-3">
                                                <h6 className="mb-0">{item.name}</h6>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                }
            </Card>
        </div>
    );
};

export default Subject;
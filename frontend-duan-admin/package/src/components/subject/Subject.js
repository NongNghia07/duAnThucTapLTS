import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { Card, CardBody, CardTitle, CardSubtitle, Table } from "reactstrap";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Modal from 'react-bootstrap/Modal';
import Form from "react-bootstrap/Form";
import { toast } from 'react-toastify';

import "../../assets/scss/subject.scss"

const Subject = () => {
    const [subject, setSubject] = useState()
    const [subjects, setSubjects] = useState()
    const navigate = useNavigate();
    const [showModalDelete, setShowModalDelete] = useState(false)
    const [showModalCreate, setShowModalCreate] = useState(false)
    const [subject_id, setSubject_id] = useState()

    const getApiSubject = useCallback(async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/v1/subject/getAllPage`);
            const data = res.data ? res.data : []
            setSubjects(data)
        } catch (error) {

        }
    }, [])

    useEffect(() => {
        getApiSubject();
    }, [])

    const openDetail = (id) => {
        navigate(`${id}`);
    }

    const callApiDeleteSubjct = async (id) => {
        try {
            await axios.post(`http://localhost:8080/api/v1/subject/delete/${id}`);
            toast.success("Xóa môn học thành công");
            getApiSubject()
        } catch (e) {
            if (e.response) {
                toast.error("Môn học này đang có khóa học sử dụng")
            }
        }
    }

    const callApiCreateSubject = async () => {
        try {
            await axios.post(`http://localhost:8080/api/v1/subject/create`, subject);
            toast.success("Thêm môn học thành công");
            getApiSubject()
        } catch (e) {
            if (e.response) {
                toast.error("Thêm môm học thất bại")
            }
        } finally {
            closeModalCreate()
        }
    }

    const deleteSubject = () => {
        callApiDeleteSubjct(subject_id)
        closeModalDelete()
    }

    const openModalDelete = (id, e) => {
        setSubject_id(id)
        setShowModalDelete(true)
        e.stopPropagation();
    }

    const closeModalDelete = () => {
        setSubject_id()
        setShowModalDelete(false)
    }

    const openModalCreate = () => {
        setShowModalCreate(true)
    }

    const closeModalCreate = () => {
        setSubject()
        setShowModalCreate(false)
    }

    return (
        <div>
            <Card>
                {subjects &&
                    <>
                        <CardTitle tag="h6" className="border-bottom p-3 mb-0">

                        </CardTitle>
                        <CardBody>
                            {subjects.content.map(item => (
                                <Button variant="primary" onClick={() => openDetail(item.id)} key={item.id} className="btn-item-subject">{item.name}
                                    <i className="bi bi-x icon-item-subject" onClick={(e) => openModalDelete(item.id, e)}></i>
                                </Button>
                            ))}
                            <Button variant="info" className="btn-add" onClick={() => openModalCreate()}>+</Button>
                        </CardBody>

                        <Modal
                            show={showModalDelete}
                            onHide={() => closeModalDelete()}
                            aria-labelledby="contained-modal-title-vcenter"
                            centered
                        >
                            <Modal.Header closeButton>
                                <Modal.Title id="contained-modal-title-vcenter">
                                    Xóa môn học
                                </Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                Bạn chắc chắn muốn xóa môn học này
                            </Modal.Body>
                            <Modal.Footer>
                                <Button variant="outline-danger" onClick={() => deleteSubject()}>Xác nhận</Button>
                                <Button variant="outline-secondary" onClick={() => closeModalDelete()}>Không</Button>
                            </Modal.Footer>
                        </Modal>

                        <Modal
                            show={showModalCreate}
                            onHide={() => closeModalCreate()}
                            aria-labelledby="contained-modal-title-vcenter"
                            centered
                        >
                            <Modal.Header closeButton>
                                <Modal.Title id="contained-modal-title-vcenter">
                                    Thêm môn học
                                </Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Form.Label htmlFor="inputPassword5">Tên môn học</Form.Label>
                                <Form.Control
                                    type="test"
                                    value={subject?.name ? subject.name : ''}
                                    onChange={(e) => setSubject((prev) => ({ ...prev, name: e.target.value }))}
                                />
                            </Modal.Body>
                            <Modal.Footer>
                                <Button variant="outline-primary" onClick={() => callApiCreateSubject()}>Thêm</Button>
                                <Button variant="outline-secondary" onClick={() => closeModalCreate()}>Đóng</Button>
                            </Modal.Footer>
                        </Modal>
                    </>
                }
            </Card>
        </div>
    );
};

export default Subject;
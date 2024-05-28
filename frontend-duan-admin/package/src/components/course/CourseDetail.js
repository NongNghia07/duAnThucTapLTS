import React, { useState, useEffect, useCallback } from "react";

import { Card, CardBody } from "reactstrap";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { useParams } from "react-router-dom";
import axios from "axios";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import { toast } from 'react-toastify';

import "../../assets/scss/courseDetail.scss"

export default function CourseDetail() {
    const [validated, setValidated] = useState(false);
    const { id } = useParams()
    const [course, setCourse] = useState()
    const [subjects, setSubjects] = useState()
    const [file, setFile] = useState()
    const animatedComponents = makeAnimated();

    // Call Api course
    const getData = useCallback(async () => {
        const resCourse = await axios.get(`http://localhost:8080/api/v1/course/find/${id}`)
        setCourse(resCourse.data)
        const resSubject = await axios.get(`http://localhost:8080/api/v1/subject/getAll`);
        updateData(resSubject.data, resCourse.data);
    }, [id])
    // End Call Api course

    // call api update course
    const updateCourse = async () => {
        const formData = new FormData();
        formData.append('courseDTO', new Blob([JSON.stringify(course)], { type: 'application/json' }));
        formData.append('file', file); // Assuming 'file' is a File object
        await axios.post(`http://localhost:8080/api/v1/course/update`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
        getData()
        toast.success("Cập nhật khóa học thành công");
    }
    // End call api update course

    useEffect(() => {
        getData()
    }, [getData])

    const setDataSubject = (subjects) => {
        try {
            let fakeData = []
            subjects.map(item => (
                fakeData.push({ label: item.name, value: item.id, subject: { id: item.id } })
            ))
            setSubjects(fakeData)
            return fakeData
        } catch (e) {

        }
    }

    const updateData = useCallback((subjects, course) => {
        if (course?.courseSubjects?.length > 0) {
            const newSubjects = setDataSubject(subjects)
            course.courseSubjects.map(item => {
                const index = newSubjects.map(p => p.value).indexOf(item.subject.id)
                if (index !== -1) {
                    item['value'] = item.subject.id
                    item['label'] = item.subject.name
                    newSubjects.splice(index, 1, item)
                }
                return item
            })
            setCourse(course)
            setSubjects(newSubjects)
        } else {
            setDataSubject(subjects)
        }
    }, [])

    const handleUpdate = () => {
        const form = document.getElementById('update-course-form').checkValidity();
        if (form === false) {
            // show điều khiện form
            setValidated(true);
            return
        }
        if (file) {
            course.imageCourse = "";
            setCourse(course)
        }
        updateCourse()
    };

    const handleInput = (input, value) => {
        if (input === 'price' || input === 'totalCourseDuration') {
            if (value !== "") {
                if (isNaN(value)) return;
                if (value <= 0) return;
            }
        }
        setCourse((prev) => ({ ...prev, [input]: value }))
    }

    const inputFile = () => {
        document.getElementById("input-file").click()
    }

    const handleSelect = (value) => {
        const data = { ...course };
        data.courseSubjects = value
        setCourse(data)
    }

    return (
        <Card>
            <CardBody>
                {course &&
                    <Form noValidate validated={validated} id='update-course-form'>
                        <Row>
                            <Col md="4">
                                <input style={{
                                    border: "1px solid",
                                    width: "100%",
                                    borderRadius: "5px",
                                    display: 'none'
                                }}
                                    id="input-file"
                                    onChange={(e) => setFile(e.target.files[0])} type="file"
                                />
                                <div className="courseDetail-img" onClick={() => inputFile()} style={{ backgroundImage: `url(${file ? URL.createObjectURL(file) : course.imageCourse})` }} />
                            </Col>
                            <Col md="8">
                                <Row className="mb-3">
                                    <Col md="4">
                                        <Form.Label>Mã khóa học: {course.code}</Form.Label>
                                    </Col>
                                    <Col md="3">
                                        <Form.Label>Số học viên: {course.numberOfStudent}</Form.Label>
                                    </Col>
                                    <Col md="5">
                                        <Form.Label>Số lượng mua hàng: {course.numberOfPurchases}</Form.Label>
                                    </Col>
                                </Row>
                                <Row className="mb-3">
                                    <Form.Group as={Col} md="6" controlId="validationCustom01">
                                        <Form.Label>Tên khóa học</Form.Label>
                                        <Form.Control
                                            required
                                            type="text"
                                            placeholder="Tên khóa học"
                                            onChange={(e) => handleInput('name', e.target.value)}
                                            value={course.name ? course.name : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">Vui lòng nhập tên khóa học</Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group as={Col} md="6" controlId="validationCustom03">
                                        <Form.Label>Giá khóa học</Form.Label>
                                        <Form.Control type="number" required
                                            onChange={(e) => handleInput('price', e.target.value)}
                                            value={course.price ? course.price : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Vui lòng nhập giá khóa học
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Row>
                                <Row className="mb-3">
                                    <Form.Group as={Col} md="6" controlId="validationCustom02">
                                        <Form.Label>Giới thiệu khóa học</Form.Label>
                                        <Form.Control
                                            required
                                            type="text"
                                            placeholder=""
                                            onChange={(e) => handleInput('introduce', e.target.value)}
                                            value={course.introduce ? course.introduce : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">Vui lòng nhập giới thiệu của khóa học</Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group as={Col} md="6" controlId="validationCustom04">
                                        <Form.Label>Thời lượng khóa học</Form.Label>
                                        <Form.Control type="number" required
                                            onChange={(e) => handleInput('totalCourseDuration', e.target.value)}
                                            value={course.totalCourseDuration ? course.totalCourseDuration : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Vui lòng nhập thời lượng khóa học
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Row>
                                <Form.Group controlId="validationCustom04">
                                    <Form.Label>Chọn môn học cho khóa học</Form.Label>
                                    <Select
                                        closeMenuOnSelect={false}
                                        components={animatedComponents}
                                        isMulti
                                        options={subjects}
                                        onChange={(e) => handleSelect(e)}
                                        value={course.courseSubjects}
                                        required
                                    />
                                    <Form.Control.Feedback type="invalid">
                                        Vui lòng chọn môn học cho khóa học
                                    </Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Button className="courseDetail-btn--save" variant="outline-primary" onClick={() => handleUpdate()}>Lưu</Button>
                    </Form>
                }
            </CardBody>
        </Card>
    )
}
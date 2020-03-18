import axios from 'axios'

const INSTRUCTOR = 'bootvue'
// const PASSWORD = 'dummy'
const COURSE_API_URL = 'http://localhost:8080'
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/instructors/${INSTRUCTOR}`

class CourseDataService {

    retrieveAllCourses() {
        //console.log('executed service')
        return axios.get(`${INSTRUCTOR_API_URL}/users`,
        );
    }
}

export default new CourseDataService()
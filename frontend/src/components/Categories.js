import styled from 'styled-components';
import Category from './Category';

const CategoriesContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-bottom: 1rem;
`;

const Categories = ({ data }) => {
    return (
        <CategoriesContainer>
            {data.map((category, index) => (
                <Category key={index} src={category.imageUrl} label={category.label} />
            ))}
        </CategoriesContainer>
    );
};

export default Categories;

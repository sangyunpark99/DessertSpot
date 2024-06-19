import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styled from "styled-components";
import banner from "../assets/banner.png";
import banner2 from "../assets/banner2.png";
import BannerItem from "./BannerItem";

const SliderContainer = styled.div`
  width: 100%;
  height: 100%;
`;

const StyledSlider = styled(Slider) `
  height: 100%;
  
  .slick-list {
    height: 100%;
    object-fit: cover;
    display: flex;
    align-items: center;
  }
  .slick-track {
    display: flex;
    align-items: center;
  }
  .slick-prev {
    left: 6px;
    z-index: 999;
  }
  .slick-next {
    right: 6px;
    z-index: 999;
  }
`;

const BannerCarousel = () => {
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: true,
        height: 100
    }

    const images = [
        {src:banner, alt:"dessert"},
        {src:banner2, alt:"dessert"},
    ]

    return (
        <SliderContainer>
            <StyledSlider {...settings}>
                {images.map((image, index) => (
                    <BannerItem
                        key={index}
                        imageSrc={image.src}
                        altText={image.alt}
                    />
                ))}
            </StyledSlider>
        </SliderContainer>
    );
}

export default BannerCarousel;
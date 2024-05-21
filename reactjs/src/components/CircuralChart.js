// src/CircularChart.js
import React, { useRef, useEffect } from 'react';
import { Doughnut } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend);

const CircularChart = ({ percentage }) => {
    const chartRef = useRef(null);

    useEffect(() => {
        const chart = chartRef.current;
        if (chart) {
            const ctx = chart.ctx;
            const gradient = ctx.createLinearGradient(0, 0, 0, ctx.canvas.height);
            gradient.addColorStop(0, '#0A9CF0');
            gradient.addColorStop(1, '#bfeafd');
            chart.data.datasets[0].backgroundColor = [gradient, 'rgba(255, 255, 255, 0.3)'];
            chart.update();
        }
    }, [percentage]);

    const data = {
        datasets: [
            {
                data: [percentage, 100 - percentage],
                borderWidth: 0,
                borderRadius: 8,
            },
        ],
    };

    const options = {
        cutout: '70%',
        plugins: {
            tooltip: {
                enabled: false,
            },
        },
        maintainAspectRatio: false,
        elements: {
            arc: {
                borderRadius: 5,
                borderSkipped: false,
            },
        },
    };

    return (
        <div style={{ position: 'relative', width: '250px', height: '250px' }}>
            <Doughnut ref={chartRef} data={data} options={options} />
            <div style={{
                position: 'absolute',
                top: '50%',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                fontSize: '45px',
                fontFamily: 'Poppins',
                fontWeight: 'bold',
                color: '#0A9CF0'
            }}>
                {`${percentage}%`}
            </div>
        </div>
    );
};

export default CircularChart;

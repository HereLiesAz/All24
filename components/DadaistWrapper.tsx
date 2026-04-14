import React, { ReactNode, useEffect, useState } from 'react';

interface DadaistWrapperProps {
  children: ReactNode;
  className?: string;
}

const DISCORDANT_HUES = [
    '#f97316', // Orange
    '#eab308', // Yellow
    '#10b981', // Emerald
    '#8b5cf6', // Violet
    '#db2777', // Pink
];

export const DadaistWrapper: React.FC<DadaistWrapperProps> = ({ children, className = '' }) => {
    const [tilt, setTilt] = useState(0);
    const [shiftX, setShiftX] = useState(0);
    const [shiftY, setShiftY] = useState(0);
    const [accentColor, setAccentColor] = useState('');

    useEffect(() => {
        // Random tilt between -1.5 and 1.5 degrees
        setTilt((Math.random() * 3) - 1.5);
        // Random shift between -4 and 4 px
        setShiftX((Math.random() * 8) - 4);
        setShiftY((Math.random() * 8) - 4);
        // Random accent color
        setAccentColor(DISCORDANT_HUES[Math.floor(Math.random() * DISCORDANT_HUES.length)]);
    }, []);

    const style = {
        '--random-tilt': `${tilt}deg`,
        '--random-shift-x': `${shiftX}px`,
        '--random-shift-y': `${shiftY}px`,
        '--random-accent': accentColor,
        transform: `rotate(var(--random-tilt)) translate(var(--random-shift-x), var(--random-shift-y))`,
    } as React.CSSProperties;

    return (
        <div style={style} className={className}>
            {children}
        </div>
    );
};

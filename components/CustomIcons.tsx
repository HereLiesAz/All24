
import React from 'react';

export const TarotCardIcon = ({ size = 24, className = "" }) => (
  <svg
    width={size}
    height={size}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
    strokeLinecap="square"
    strokeLinejoin="miter"
    className={className}
  >
    <rect x="5" y="3" width="14" height="18" rx="0" />
    <path d="M8 7h8M8 11h8M8 15h8" opacity="0.5" strokeWidth="1"/>
    <circle cx="12" cy="12" r="2" />
  </svg>
);

export const IsolatedStarIcon = ({ size = 24, className = "" }) => (
  <svg
    width={size}
    height={size}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="3"
    strokeLinecap="square"
    className={className}
  >
    <path d="M12 2v20M2 12h20M7 7l10 10M17 7L7 17" />
    <circle cx="12" cy="12" r="1.5" fill="currentColor" />
  </svg>
);

export const PixelPinIcon = ({ size = 24, className = "" }) => (
  <svg
    width={size}
    height={size}
    viewBox="0 0 24 24"
    fill="currentColor"
    className={className}
    shapeRendering="crispEdges"
  >
    <rect x="8" y="2" width="8" height="2" />
    <rect x="6" y="4" width="12" height="2" />
    <rect x="4" y="6" width="16" height="6" />
    <rect x="6" y="12" width="12" height="2" />
    <rect x="8" y="14" width="8" height="2" />
    <rect x="10" y="16" width="4" height="2" />
    <rect x="11" y="18" width="2" height="4" />
    <rect x="8" y="6" width="2" height="2" fill="var(--bg-color)" /> {/* Hollow eye look */}
  </svg>
);

export const PixelAlligatorIcon = ({ size = 24, className = "" }) => (
  <svg
    width={size}
    height={size}
    viewBox="0 0 32 32"
    fill="currentColor"
    className={className}
    shapeRendering="crispEdges"
  >
    {/* Detailed Pixel Alligator based on the prompt's second image */}
    {/* Body */}
    <rect x="6" y="14" width="18" height="6" />
    <rect x="4" y="16" width="2" height="2" /> {/* Tail start */}
    <rect x="2" y="14" width="2" height="2" /> {/* Tail tip */}

    {/* Legs */}
    <rect x="8" y="20" width="3" height="3" />
    <rect x="20" y="20" width="3" height="3" />

    {/* Head/Jaws - Open */}
    <rect x="24" y="12" width="6" height="3" /> {/* Top Jaw */}
    <rect x="24" y="17" width="6" height="3" /> {/* Bottom Jaw */}
    <rect x="22" y="11" width="2" height="2" /> {/* Eye bump */}

    {/* Teeth/Spikes (Red accents handled by color class) */}
    <rect x="8" y="12" width="2" height="2" opacity="0.5" />
    <rect x="12" y="12" width="2" height="2" opacity="0.5" />
    <rect x="16" y="12" width="2" height="2" opacity="0.5" />

    {/* Eye */}
    <rect x="23" y="11.5" width="1" height="1" fill="white" />
  </svg>
);
